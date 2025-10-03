/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.oaotc

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


object TransferDetailsJourneyRequests extends BaseRequests {
  //Possibly remove this val as doesn't look to be used?
  lazy val TransferDetailsUrl: String = otcRedirectUrl + "/transfer-details/"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  def getTypeOfAsset: HttpRequestBuilder =
    http("GET - Type of asset page")
      .get(TransferDetailsUrl+"assets/type-of-asset")
      .check(status.is(200))
      .check(saveCsrfToken())



  def postTypeOfAsset(valuePosition:String , value: String): HttpRequestBuilder =
    http("POST - Type of asset page")
      .post(TransferDetailsUrl+"assets/type-of-asset")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value"+valuePosition, value)
      .check(status.is(303))

  def postTypeOfMultipleAssets(valuePosition:String , value: String,valuePositionTwo:String , valueTwo: String): HttpRequestBuilder =
    http("POST - Multiple type selection of assets page")
      .post(TransferDetailsUrl+"assets/type-of-asset")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value"+valuePosition, value)
      .formParam("value"+valuePositionTwo, valueTwo)
      .check(status.is(303))


  // Quoted shares methods

  def getQuotedSharesStart: HttpRequestBuilder =
    http("GET - Quoted shares start page")
      .get(TransferDetailsUrl+"assets/quoted-shares-start")
      .check(status.is(200))


  def getQuotedSharesCompanyName: HttpRequestBuilder =
    http("GET - Quoted shares company name page")
      .get(TransferDetailsUrl+"assets/quoted-shares-company-name?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQuotedSharesCompanyName : HttpRequestBuilder =
    http("POST - Quoted shares company name page")
      .post(TransferDetailsUrl+"assets/quoted-shares-company-name?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "company name")
      .check(status.is(303))

  def getQuotedSharesValue: HttpRequestBuilder =
    http("GET - Quoted shares value page")
      .get(TransferDetailsUrl+"assets/quoted-shares-value?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQuotedSharesValue : HttpRequestBuilder =
    http("POST - Quoted shares value page")
      .post(TransferDetailsUrl+"assets/quoted-shares-value?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "60000")
      .check(status.is(303))

  def getQuotedSharesNumber: HttpRequestBuilder =
    http("GET - Quoted shares number page")
      .get(TransferDetailsUrl+"assets/quoted-shares-number?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQuotedSharesNumber : HttpRequestBuilder =
    http("POST - Quoted shares number page")
      .post(TransferDetailsUrl+"assets/quoted-shares-number?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "60")
      .check(status.is(303))

  def getQuotedSharesClass: HttpRequestBuilder =
    http("GET - Quoted shares class page")
      .get(TransferDetailsUrl+"assets/quoted-shares-class?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQuotedSharesClass : HttpRequestBuilder =
    http("POST - Quoted shares class page")
      .post(TransferDetailsUrl+"assets/quoted-shares-class?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "A")
      .check(status.is(303))

  def getQuotedSharesCheckYourAnswers: HttpRequestBuilder =
    http("GET - Quoted shares check your answers page")
      .get(TransferDetailsUrl+"assets/quoted-shares-check-your-answers?index=0")
      .check(status.is(200))


  // Unquoted shares methods

  def getUnquotedSharesStart: HttpRequestBuilder =
    http("GET - Unquoted shares start page")
      .get(TransferDetailsUrl+"assets/unquoted-shares-start")
      .check(status.is(200))


  def getUnquotedSharesCompanyName: HttpRequestBuilder =
    http("GET - Unquoted shares company name page")
      .get(TransferDetailsUrl+"assets/unquoted-shares-company-name?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postUnquotedSharesCompanyName : HttpRequestBuilder =
    http("POST - Unquoted shares company name page")
      .post(TransferDetailsUrl+"assets/unquoted-shares-company-name?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "company name")
      .check(status.is(303))

  def getUnquotedSharesValue: HttpRequestBuilder =
    http("GET - Unquoted shares value page")
      .get(TransferDetailsUrl+"assets/unquoted-shares-value?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postUnquotedSharesValue : HttpRequestBuilder =
    http("POST - Unquoted shares value page")
      .post(TransferDetailsUrl+"assets/unquoted-shares-value?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "60000")
      .check(status.is(303))

  def getUnquotedSharesNumber: HttpRequestBuilder =
    http("GET - unquoted shares number page")
      .get(TransferDetailsUrl+"assets/unquoted-shares-number?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postUnquotedSharesNumber : HttpRequestBuilder =
    http("POST - unquoted shares number page")
      .post(TransferDetailsUrl+"assets/unquoted-shares-number?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "60")
      .check(status.is(303))

  def getUnquotedSharesClass: HttpRequestBuilder =
    http("GET - Unquoted shares class page")
      .get(TransferDetailsUrl+"assets/unquoted-shares-class?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postUnquotedSharesClass : HttpRequestBuilder =
    http("POST - Unquoted shares class page")
      .post(TransferDetailsUrl+"assets/unquoted-shares-class?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "A")
      .check(status.is(303))

  def getUnquotedSharesCheckYourAnswers: HttpRequestBuilder =
    http("GET - Unquoted shares check your answers page")
      .get(TransferDetailsUrl+"assets/unquoted-shares-check-your-answers?index=0")
      .check(status.is(200))

//  Property methods

  def getPropertyStart: HttpRequestBuilder =
    http("GET - Property start page")
      .get(TransferDetailsUrl+"assets/property-start")
      .check(status.is(200))


  def getPropertyAddress: HttpRequestBuilder =
    http("GET - Property address page")
      .get(TransferDetailsUrl+"assets/property-address?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postPropertyAddress : HttpRequestBuilder =
    http("POST - Unquoted shares company name page")
      .post(TransferDetailsUrl+"assets/property-address?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("addressLine1", "line 1")
      .formParam("addressLine2", "line 2")
      .formParam("countryCode", "GB")
      .formParam("postcode", "ukpost")
      .check(status.is(303))

  def getPropertyValue: HttpRequestBuilder =
    http("GET - Property value page")
      .get(TransferDetailsUrl+"assets/property-value?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postPropertyValue : HttpRequestBuilder =
    http("POST - Property value page")
      .post(TransferDetailsUrl+"assets/property-value?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "60000")
      .check(status.is(303))

  def getPropertyDescription: HttpRequestBuilder =
    http("GET - Property description page")
      .get(TransferDetailsUrl+"assets/property-description?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postPropertyDescription : HttpRequestBuilder =
    http("POST - Property description page")
      .post(TransferDetailsUrl+"assets/property-description?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "prop description")
      .check(status.is(303))

  def getPropertyCheckYourAnswers: HttpRequestBuilder =
    http("GET - Unquoted shares check your answers page")
      .get(TransferDetailsUrl+"assets/property-check-your-answers?index=0")
      .check(status.is(200))

  //  Cash and other assets methods

  def getCashInTransfer: HttpRequestBuilder =
    http("GET - Cash in transfer page")
      .get(TransferDetailsUrl+"assets/cash-amount-in-transfer")
      .check(status.is(200))

  def postCashInTransfer : HttpRequestBuilder =
    http("POST - Cash in transfer page")
      .post(TransferDetailsUrl+"assets/cash-amount-in-transfer")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("cashInTransfer", "60000")
      .check(status.is(303))

  def getOtherAssetsStart: HttpRequestBuilder =
    http("GET - Other assets start page")
      .get(TransferDetailsUrl+"assets/other-assets-start")
      .check(status.is(200))


  def getOtherAssetsDescription: HttpRequestBuilder =
    http("GET - Other assets description page")
      .get(TransferDetailsUrl+"assets/other-assets-description?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postOtherAssetsDescription : HttpRequestBuilder =
    http("POST - Other assets description page")
      .post(TransferDetailsUrl+"assets/other-assets-description?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "assets description")
      .check(status.is(303))

  def getOtherAssetsValue: HttpRequestBuilder =
    http("GET - Other assets value page")
      .get(TransferDetailsUrl+"assets/other-assets-value?index=0")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postOtherAssetsValue : HttpRequestBuilder =
    http("POST - Other assets value page")
      .post(TransferDetailsUrl+"assets/other-assets-value?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "60000")
      .check(status.is(303))

  def getOtherAssetsCheckYourAnswers: HttpRequestBuilder =
    http("GET - Other assets check your answers page")
      .get(TransferDetailsUrl+"assets/other-assets-check-your-answers?index=0")
      .check(status.is(200))

  def postOtherAssetsCheckYourAnswers: HttpRequestBuilder =
    http("Post - Other assets check your answers page")
      .get(TransferDetailsUrl+"assets/other-assets-check-your-answers?index=0")
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(200))

  def getOtherAssetsAmendContinue: HttpRequestBuilder =
    http("GET - Other assets amend continue page")
      .get(TransferDetailsUrl+"assets/other-assets-amend-continue")
      .check(status.is(200))

  def postOtherAssetsAmendContinue: HttpRequestBuilder =
    http("POST - Other assets amend continue page")
      .get(TransferDetailsUrl+"assets/other-assets-amend-continue")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("add-another", "No")
      .check(status.is(200))

  def getCheckYourAnswers: HttpRequestBuilder =
    http("GET - Check your answers page")
      .get(TransferDetailsUrl+"check-your-answers")
      .check(status.is(200))

}

