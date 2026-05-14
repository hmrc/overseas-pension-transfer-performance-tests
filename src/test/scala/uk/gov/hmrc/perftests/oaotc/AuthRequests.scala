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
import io.gatling.core.session.el.ElCompiler
import io.gatling.core.session.StaticValueExpression
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object AuthRequests extends BaseRequests {

  lazy val getAuthWizard: HttpRequestBuilder =
    http("GET - Auth wizard")
      .get(authWizardUrl)
      .check(status.is(200))

  def postLoginAsPspUser(psaid: String, redirectUrl: String = otcRedirectUrl): HttpRequestBuilder =
    http("POST - Login as a Psa user")
      .post(authWizardUrl)
      .formParam("csrfToken", ElCompiler.parse("$${csrfToken}"))
      .formParam("redirectionUrl", StaticValueExpression(redirectUrl))
      .formParam("credentialStrength", StaticValueExpression("strong"))
      .formParam("authorityId", StaticValueExpression(""))
      .formParam("confidenceLevel", StaticValueExpression("50"))
      .formParam("affinityGroup", StaticValueExpression("Individual"))
      .formParam("gatewayToken", StaticValueExpression(""))
      .formParam("enrolment[0].name", StaticValueExpression("HMRC-PODS-ORG"))
      .formParam("enrolment[0].taxIdentifier[0].name", StaticValueExpression("PSAID"))
      .formParam("enrolment[0].taxIdentifier[0].value", StaticValueExpression(psaid))
      .formParam("enrolment[0].state", StaticValueExpression("Activated"))
      .formParam("presets-dropdown", StaticValueExpression("IR-SA"))
      .formParam("credentialRole", StaticValueExpression("User"))
      .formParam("email", StaticValueExpression("user@test.com"))
      .formParam("excludeGnapToken", false)
      .check(status.is(303))
      .check(header(StaticValueExpression("Location")).is(StaticValueExpression(redirectUrl)))

}
