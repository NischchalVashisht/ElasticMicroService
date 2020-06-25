package com.knoldus.daml.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.daml.routes.DamlData.InfectedPerson.InfectedPersonData
import infectedservice.ElasticSearchService
import akka.event.LoggingAdapter

class InfectedPersonRoutes(logger: LoggingAdapter,patientData : ElasticSearchService) extends BaseRoutes {

  logger.info("route for analysis available")
  val routes: Route =
    path( "analysis") {
      (post & entity(as[InfectedPersonData])) { infectedPerson =>
        onSuccess(patientData.insert(infectedPerson)) {
          result =>
            complete(result)
        }
      }
    }

}
