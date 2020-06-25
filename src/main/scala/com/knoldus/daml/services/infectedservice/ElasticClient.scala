package infectedservice

import org.apache.http.HttpHost
import org.elasticsearch.client.{RestClient, RestHighLevelClient}

object ElasticClient {

  private val port = 9200
  private val host = "localhost"
  private val scheme = "http"

  val client = new RestHighLevelClient(
    RestClient.builder(new HttpHost(host, port, scheme))
  )
}
