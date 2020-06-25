package com.knoldus.daml.routes.DamlData.common

import play.api.libs.json.{ JsValue, Json, OWrites }


case class ErrorResponse(
                          code: Int,
                          message: String,
                          reason: Option[String] = None,
                          errors: Seq[JsValue] = Seq.empty
                        )


