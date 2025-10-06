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


object  QROPSJourneyRequests extends BaseRequests {
  //Possibly remove this val as doesn't look to be used?
  lazy val MemberDetailsUrl: String = otcRedirectUrl + "/qrops-details/"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  def getQROPSName: HttpRequestBuilder =
    http("GET - QROPS Name page")
      .get(MemberDetailsUrl + "qrops-name")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQROPSName(qropsName: String): HttpRequestBuilder =
    http("POST - QROPS Name page")
      .post(MemberDetailsUrl + "qrops-name")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("qropsName", qropsName)
      .check(status.is(303))

  def getQROPSRef: HttpRequestBuilder =
    http("GET - QROPS Reference page")
      .get(MemberDetailsUrl + "qrops-reference")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQROPSRef(qropsRef: String): HttpRequestBuilder =
    http("POST - QROPS Reference page")
      .post(MemberDetailsUrl + "qrops-reference")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("qropsRef", qropsRef)
      .check(status.is(303))

  def getQROPSAddress: HttpRequestBuilder =
    http("GET - QROPS Address page")
      .get(MemberDetailsUrl + "qrops-address")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQROPSAddress(qropsAddress1: String, qropsAddress2: String, qropsCountry: String): HttpRequestBuilder =
    http("POST - QROPS Address page")
      .post(MemberDetailsUrl + "qrops-address")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("addressLine1", qropsAddress1)
      .formParam("addressLine2", qropsAddress2)
      .formParam("countryCode", qropsCountry)
      .check(status.is(303))

  def getQROPSCountry: HttpRequestBuilder =
    http("GET - QROPS Country page")
      .get(MemberDetailsUrl + "qrops-country")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postQROPSCountry(qropsCountryLookup: String): HttpRequestBuilder =
    http("POST - QROPS Country page")
      .post(MemberDetailsUrl + "qrops-country")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("countryCode", qropsCountryLookup)
      .check(status.is(303))

  def getQROPSCYA: HttpRequestBuilder =
    http("GET - QROPS CYA page")
      .get(MemberDetailsUrl + "qrops-country")
      .check(status.is(200))
      .check(saveCsrfToken())

}