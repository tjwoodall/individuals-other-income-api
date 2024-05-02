/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package api.models.errors

import play.api.http.Status._

object EmploymentIdFormatError extends MtdError("FORMAT_EMPLOYMENT_ID", "The provided employment ID is invalid", BAD_REQUEST)
object CountryCodeRuleError    extends MtdError("RULE_COUNTRY_CODE", "The country code is not a valid ISO 3166-1 alpha-3 country code", BAD_REQUEST)
object CustomerRefFormatError  extends MtdError("FORMAT_CUSTOMER_REF", "The provided customer reference is invalid", BAD_REQUEST)

object DateFormatError extends MtdError("FORMAT_DATE", "The field should be in the format YYYY-MM-DD", BAD_REQUEST)

object RuleUnalignedCessationTaxYear
    extends MtdError("RULE_UNALIGNED_CESSATION_TAX_YEAR", "The tax year provided must be the same as the tax year of income to be taxed", BAD_REQUEST)

object RuleRequestCannotBeFulfilled
    extends MtdError("RULE_REQUEST_CANNOT_BE_FULFILLED", "Custom (will vary in production depending on the actual error)", 422)
