package infectedservice

import akka.actor.ActorSystem
import infectedservice.ElasticSearchService._
import org.elasticsearch.action.delete.{DeleteRequest, DeleteResponse}
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.{SearchRequest, SearchResponse}
import org.elasticsearch.client.{RequestOptions, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import com.knoldus.daml.routes.DamlData.InfectedPerson.InfectedPersonData
import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.daml.routes.DamlData.jsonProtocol.JsonProtocol
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import scala.concurrent.{ExecutionContextExecutor, Future}

object ElasticSearchService {

  val Index = "covid-info"
  val Type = "infected"
}

class ElasticSearchService(client: RestHighLevelClient)(implicit val system: ActorSystem, implicit val executionContext: ExecutionContextExecutor) extends JsonProtocol with SprayJsonSupport{

  def insert(infected: InfectedPersonData): Future[HttpResponse] = Future{
    val indexRequest = new IndexRequest(Index).id(infected.id.toString)
    val jsonString = infected.toJson.toString
    indexRequest.source(jsonString, XContentType.JSON)
    client.index(indexRequest,RequestOptions.DEFAULT)
    HttpResponse(StatusCodes.OK)
  }

  
}
