package com.knoldus.daml.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.http.scaladsl.unmarshalling.FromStringUnmarshaller
import akka.stream.Materializer
import com.knoldus.daml.routes.DamlData.common.ErrorResponse
import com.knoldus.daml.routes.DamlData.jsonProtocol.JsonProtocol
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport.PlayJsonError
import com.knoldus.daml.utils.TryExtensions._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

trait BaseRoutes extends JsonProtocol with SprayJsonSupport{

  protected def errorResponse(code: StatusCode, error: String): (StatusCode, ErrorResponse) =
    (code, ErrorResponse(code.intValue, error))

  protected def fromStringUnmarshaller[A: Reads]: FromStringUnmarshaller[A] =
    new FromStringUnmarshaller[A] {
      override def apply(value: String)(implicit ec: ExecutionContext, materializer: Materializer): Future[A] =
        Try {
          implicitly[Reads[A]].reads(JsString(value)).recoverTotal { e =>
            throw RejectionError(
              ValidationRejection(JsError.toJson(e).toString, Some(PlayJsonError(e)))
            )
          }
        }.toFuture
    }

  protected def optionWithEmptyString[A: Reads]: Reads[Option[A]] =
    Reads[Option[A]] {
      case jsString: JsString if jsString.value.nonEmpty =>
        implicitly[Reads[A]].reads(jsString).map(Some(_))
      case _ =>
        JsSuccess(None)
    }

  protected def segment[T](f: PartialFunction[String, T]): PathMatcher1[T] =
    PathMatchers.Segment.flatMap(f.lift(_))

}



