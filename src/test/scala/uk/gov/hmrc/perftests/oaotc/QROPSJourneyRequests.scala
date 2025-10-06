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
    http("POST - Member Name page")
      .post(MemberDetailsUrl + "qrops-name")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("qropsName", qropsName)
      .check(status.is(303))

}