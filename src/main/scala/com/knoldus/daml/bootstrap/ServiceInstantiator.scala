package com.knoldus.daml.bootstrap

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import infectedservice.{ElasticClient, ElasticSearchService}
import scala.concurrent.ExecutionContextExecutor
import org.elasticsearch.client.RestHighLevelClient

class ServiceInstantiator(logger: LoggingAdapter) (implicit system: ActorSystem,implicit val executionContext: ExecutionContextExecutor) {

  logger.info("elastic service started")
  val client: RestHighLevelClient = ElasticClient.client
  val elasticSearchService = new ElasticSearchService(client)

}
