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


object MemberJourneyRequests extends BaseRequests {
  //Possibly remove this val as doesn't look to be used?
  lazy val MemberDetailsUrl: String = otcRedirectUrl + "/member-details/"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  def getMemberName: HttpRequestBuilder =
    http("GET - Member Name page")
      .get(MemberDetailsUrl+"member-name")
      .check(status.is(200))
      .check(saveCsrfToken())



  def postMemberName(memberFirstName: String, memberLastName: String): HttpRequestBuilder =
    http("POST - Member Name page")
      .post(MemberDetailsUrl+"member-name")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("memberFirstName", memberFirstName)
      .formParam("memberLastName", memberLastName)
      .check(status.is(303))

  def getMemberNino: HttpRequestBuilder =
    http("GET - Member Nino page")
      .get(MemberDetailsUrl+"member-nino")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postMemberNino: HttpRequestBuilder =
    http("POST - Member Nino page")
      .post(MemberDetailsUrl+"member-nino")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", "QQ123456A")
      .check(status.is(303))

  def getMemberDOB: HttpRequestBuilder =
    http("GET - Member DOB page")
      .get(MemberDetailsUrl+"member-date-of-birth")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postMemberDOB: HttpRequestBuilder =
    http("POST - Member DOB page")
      .get(MemberDetailsUrl+"member-date-of-birth")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value.day", "12")
      .formParam("value.month", "1")
      .formParam("value.year", "2023")
      .check(status.is(200))

  def getMemberCurrentAddress: HttpRequestBuilder =
    http("GET - Member Current address page")
      .get(MemberDetailsUrl+"members-current-address")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postMemberCurrentAddress: HttpRequestBuilder =
    http("POST - Member Current address page")
      .get(MemberDetailsUrl+"member-date-of-birth")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("addressLine1", "line 1")
      .formParam("addressLine2", "line 2")
      .formParam("countryCode", "GB")
      .formParam("postcode", "ukpost")
      .check(status.is(200))

  def getMemberIsResidentUk: HttpRequestBuilder =
    http("GET - Member is resident uk page")
      .get(MemberDetailsUrl+"member-is-resident-uk")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postMemberIsResidentUk(value: Boolean): HttpRequestBuilder =
    http("POST - Member is resident uk page")
      .get(MemberDetailsUrl+"member-is-resident-uk")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", value)
      .check(status.is(200))

  def getMemberHasEverBeenResidentUk: HttpRequestBuilder =
    http("GET - Member has ever been resident uk page")
      .get(MemberDetailsUrl+"member-has-ever-been-resident-uk")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postMemberHasEverBeenResidentUk(value: Boolean): HttpRequestBuilder =
    http("POST - Member has ever been resident uk page")
      .get(MemberDetailsUrl+"member-has-ever-been-resident-uk")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", value)
      .check(status.is(200))

  def getMemberCheckYourAnswers: HttpRequestBuilder =
    http("GET - Member check your answers page")
      .get(MemberDetailsUrl+"check-your-answers")
      .check(status.is(200))
      .check(saveCsrfToken())
}
