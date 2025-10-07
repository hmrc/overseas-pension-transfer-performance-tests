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


object SchemaManagerJourneyRequests extends BaseRequests {
  //Possibly remove this val as doesn't look to be used?
  lazy val SchemeManagerUrl: String = otcRedirectUrl + "/qrops-scheme-manager-details/"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  def getTypeOfSchemeManager: HttpRequestBuilder =
    http("GET - Scheme manager type page")
      .get(SchemeManagerUrl+"scheme-manager-type")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postTypeOfSchemeManager(value:String): HttpRequestBuilder =
    http("POST - Scheme manager type page")
      .post(SchemeManagerUrl+"scheme-manager-type")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  def getNameOfSchemeManager: HttpRequestBuilder =
  http("GET - Scheme manager name page")
    .get(SchemeManagerUrl+"scheme-managers-name")
    .check(status.is(200))
    .check(saveCsrfToken())

  def postNameOfSchemeManager(memberFirstName: String, memberLastName: String): HttpRequestBuilder =
    http("POST - Scheme manager name page")
      .post(SchemeManagerUrl+"scheme-managers-name")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("schemeManagersFirstName", memberFirstName)
      .formParam("schemeManagersLastName", memberLastName)
      .check(status.is(303))


  def getSchemeManagerAddress: HttpRequestBuilder =
    http("GET - Scheme manager address page")
      .get(SchemeManagerUrl+"scheme-managers-address")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postSchemeManagerAddress: HttpRequestBuilder =
    http("POST - Scheme manager address page")
      .get(SchemeManagerUrl+"scheme-managers-address")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("addressLine1", "line 1")
      .formParam("addressLine2", "line 2")
      .formParam("countryCode", "GB")
      .formParam("postcode", "ukpost")
      .check(status.is(200))

  def getSchemeManagerEmail: HttpRequestBuilder =
    http("GET - Scheme manager email page")
      .get(SchemeManagerUrl+"scheme-managers-email")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postSchemeManagerEmail: HttpRequestBuilder =
    http("POST - Scheme manager email page")
      .get(SchemeManagerUrl+"scheme-managers-email")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("emailAddress", "name@example.com")
      .check(status.is(200))

  def getSchemeManagerContact: HttpRequestBuilder =
    http("GET - Scheme manager contact page")
      .get(SchemeManagerUrl+"scheme-managers-contact")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postSchemeManagerContact: HttpRequestBuilder =
    http("POST - Scheme manager contact page")
      .get(SchemeManagerUrl+"scheme-managers-contact")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("contactNumber", "+441234567890")
      .check(status.is(200))

  def getSchemeManagerCheckYourAnswers: HttpRequestBuilder =
    http("GET - Member check your answers page")
      .get(SchemeManagerUrl+"check-your-answers")
      .check(status.is(200))
      .check(saveCsrfToken())

  def getNameOfOrganisation: HttpRequestBuilder =
    http("GET - organisation name page")
      .get(SchemeManagerUrl+"organisation-name")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postNameOfOrganisation: HttpRequestBuilder =
    http("POST -organisation name page")
      .post(SchemeManagerUrl+"organisation-name")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("organisationName", "OrganisationName")
      .check(status.is(303))


  def getNameOfOrganisationIndividual: HttpRequestBuilder =
    http("GET - Scheme manager name page")
      .get(SchemeManagerUrl+"organisation-individual-name")
      .check(status.is(200))
      .check(saveCsrfToken())

  def postNameOfOrganisationIndividual: HttpRequestBuilder =
    http("POST - Scheme manager name page")
      .post(SchemeManagerUrl+"organisation-individual-name")
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("orgIndFirstName", "FirstName")
      .formParam("orgIndLastName", "LastName")
      .check(status.is(303))

}

