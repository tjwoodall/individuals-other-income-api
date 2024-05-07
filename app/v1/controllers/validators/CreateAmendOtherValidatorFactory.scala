/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package v1.controllers.validators

import config.AppConfig
import play.api.libs.json.JsValue

import javax.inject.{Inject, Singleton}

@Singleton
class CreateAmendOtherValidatorFactory @Inject() (appConfig: AppConfig) {

  def validator(nino: String, taxYear: String, body: JsValue, appConfig: AppConfig) =
    new CreateAmendOtherValidator(nino, taxYear, body, appConfig)

}
