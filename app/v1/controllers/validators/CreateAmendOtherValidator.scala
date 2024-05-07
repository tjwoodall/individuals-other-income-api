/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package v1.controllers.validators

import api.controllers.validators.Validator
import api.controllers.validators.resolvers._
import api.models.errors.MtdError
import cats.data.Validated
import cats.implicits._
import config.AppConfig
import play.api.libs.json.JsValue
import v1.models.request.createAmendOther.{CreateAmendOtherRequest, CreateAmendOtherRequestBody}

object CreateAmendOtherValidator {

  private val resolveJson = ResolveNonEmptyJsonObject.resolver[CreateAmendOtherRequestBody]

}

class CreateAmendOtherValidator(nino: String, taxYear: String, body: JsValue, appConfig: AppConfig) extends Validator[CreateAmendOtherRequest] {
  import CreateAmendOtherValidator._

  private val resolveTaxYear = ResolveTaxYearMinimum(appConfig.minimumPermittedTaxYear)

  override def validate: Validated[Seq[MtdError], CreateAmendOtherRequest] =
    (
      ResolveNino(nino),
      resolveTaxYear(taxYear),
      resolveJson(body)
    ).mapN(CreateAmendOtherRequest) andThen CreateAmendOtherRulesValidator.validateBusinessRules

}
