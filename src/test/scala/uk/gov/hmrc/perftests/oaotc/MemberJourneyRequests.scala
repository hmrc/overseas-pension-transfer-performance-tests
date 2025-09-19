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
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


object MemberJourneyRequests extends BaseRequests {
  //Possibly remove this val as doesn't look to be used?
  lazy val MemberDetailsUrl: String = otcRedirectUrl + "/member-details/"

  /*def getHomeWithOrigin(redirectUrl: String): HttpRequestBuilder =
    http("GET - Home page with origin")
      .get(redirectUrl)
      .check(status.is(303))*/

  def getMemberName: HttpRequestBuilder =
    http("GET - Member Name page")
      .get(MemberDetailsUrl+"member-name")
      .check(status.is(303))

  def postMemberName(memberFirstName: String, memberLastName: String) =
    http("POST - Member Name page")
    .post(MemberDetailsUrl+"member-name")
    .formParam("csrfToken", "#{csrfToken}")
    .formParam("memberFirstName", memberFirstName)
    .formParam("memberLastName", memberLastName)
    .check(status.is(303))
}
