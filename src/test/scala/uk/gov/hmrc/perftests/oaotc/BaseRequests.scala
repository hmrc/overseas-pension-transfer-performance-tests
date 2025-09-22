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
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import scala.concurrent.duration.DurationInt

trait BaseRequests extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("report-transfer-qualified-recognised-overseas-pension-scheme")

  val otcRedirectUrl: String = baseUrl + "/report-transfer-qualified-recognised-overseas-pension-scheme"

  val otcSrnUrl: String = baseUrl + "/report-transfer-qualified-recognised-overseas-pension-scheme/start?srn=S2400000001"

  val otcDashBoardUrl: String = otcRedirectUrl + "/dashboard"

  val otcWhatWillBeNeededUrl: String = otcRedirectUrl + "/what-will-be-needed"

  val authWizardUrl: String = baseUrlFor("auth-login-stub") + "/auth-login-stub/gg-sign-in"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def sessionPause(int: Int) = pause(int second)
}

