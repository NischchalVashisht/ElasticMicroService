package com.knoldus.daml.bootstrap

import com.knoldus.daml.routes.InfectedPersonRoutes
import akka.http.scaladsl.server.Directives.{concat, ignoreTrailingSlash}
import akka.http.scaladsl.server.Route
import akka.event.LoggingAdapter

class RouteInstantiator(services:ServiceInstantiator,logger: LoggingAdapter) {

  private  val patientRoute = new InfectedPersonRoutes(logger,services.elasticSearchService).routes
  logger.info("Route instantiated")
  val routes: Route =
    ignoreTrailingSlash {
      concat(
       patientRoute
      )
  }

}
