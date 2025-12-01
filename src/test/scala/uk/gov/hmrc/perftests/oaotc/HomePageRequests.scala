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


object HomePageRequests extends BaseRequests {
  lazy val homeUrl: String = otcRedirectUrl + "/start?srn=S2400000001"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  /*def getHomeWithOrigin(redirectUrl: String): HttpRequestBuilder =
    http("GET - Home page with origin")
      .get(redirectUrl)
      .check(status.is(303))*/

  def getHome: HttpRequestBuilder =
    http("GET - Home page")
      .get(homeUrl)
      .check(status.is(303))

  val getDashBoardPage: HttpRequestBuilder =
    http("Get Dashboard Page")
      .get(otcDashBoardUrl)
      .check(status.is(200))


  val getWhatWillBeNeededPage: HttpRequestBuilder =
    http("Get What will be needed Page")
      .get(otcWhatWillBeNeededUrl)
      .check(status.is(200))
      .check(saveCsrfToken())


  val postWhatWillBeNeededPage: HttpRequestBuilder =
    http("Post What will be needed Page")
      .post(otcWhatWillBeNeededUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val getTaskListPage: HttpRequestBuilder =
    http("Get task list Page")
      .get(otcTaskListUrl)
      .check(status.is(200))

}
