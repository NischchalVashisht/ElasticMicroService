package com.knoldus.daml.routes.DamlData.jsonProtocol

import com.knoldus.daml.routes.DamlData.InfectedPerson.{InfectedPersonData, Location}
import spray.json._

trait JsonProtocol extends DefaultJsonProtocol {

  implicit val location: RootJsonFormat[Location] = jsonFormat2(Location)
  implicit val infectedPerson: RootJsonFormat[InfectedPersonData] = jsonFormat6(InfectedPersonData)


}
