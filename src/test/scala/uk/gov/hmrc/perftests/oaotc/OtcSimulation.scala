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

package uk.gov.hmrc.perftests.oaotc

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.oaotc.AuthRequests._
import uk.gov.hmrc.perftests.oaotc.HomePageRequests._
import uk.gov.hmrc.perftests.oaotc.MemberJourneyRequests._
import uk.gov.hmrc.perftests.oaotc.QROPSJourneyRequests._
import uk.gov.hmrc.perftests.oaotc.SchemaManagerJourneyRequests._
import uk.gov.hmrc.perftests.oaotc.TransferDetailsJourneyRequests._

class OtcSimulation extends PerformanceTestRunner {

  setup("LoginAndNavigateToTaskList", "Login and navigate to task list").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid = "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage
  )

  setup("MemberBasicDetails", "Enter member basic details").withRequests(
    getMemberName,
    postMemberName("FirstName", "LastName"),
    getMemberNino,
    postMemberNino,
    getMemberDOB,
    postMemberDOB
  )

  setup("MemberCurrentAddress", "Enter member current address").withRequests(
    getMemberCurrentAddress,
    postMemberCurrentAddress
  )

  setup("MemberIsUkResident", "Member is UK resident").withRequests(
    getMemberIsResidentUk,
    getMemberCheckYourAnswers
  )

  setup("MemberIsNotUkResident", "Member is not UK resident").withRequests(
    getMemberIsResidentUk,
    postMemberIsResidentUk(false),
    getMemberHasEverBeenResidentUk,
    postMemberHasEverBeenResidentUk(false),
    getMemberCheckYourAnswers
  )

  setup("TransferQuotedShareSelection", "Select quoted shares asset type").withRequests(
    getTypeOfAsset,
    postTypeOfAsset("[2]", "quotedShareAssets"),
    getQuotedSharesStart
  )

  setup("TransferQuotedShareDetails", "Enter quoted shares details").withRequests(
    getQuotedSharesCompanyName,
    postQuotedSharesCompanyName,
    getQuotedSharesValue,
    postQuotedSharesValue,
    getQuotedSharesNumber,
    postQuotedSharesNumber,
    getQuotedSharesClass,
    postQuotedSharesClass,
    getQuotedSharesCheckYourAnswers
  )

  setup("TransferUnquotedShareSelection", "Select unquoted shares asset type").withRequests(
    getTypeOfAsset,
    postTypeOfAsset("[1]", "unquotedShareAssets")
  )

  setup("TransferUnquotedShareDetails", "Enter unquoted shares details").withRequests(
    getUnquotedSharesClass,
    getUnquotedSharesCompanyName,
    postUnquotedSharesCompanyName,
    getUnquotedSharesValue,
    postUnquotedSharesValue,
    getUnquotedSharesNumber,
    postUnquotedSharesNumber,
    getUnquotedSharesClass,
    postUnquotedSharesClass,
    getUnquotedSharesCheckYourAnswers
  )

  setup("TransferPropertySelection", "Select property asset type").withRequests(
    getTypeOfAsset,
    postTypeOfAsset("[3]", "propertyAsset"),
    getPropertyStart
  )

  setup("TransferPropertyDetails", "Enter property details").withRequests(
    getPropertyAddress,
    postPropertyAddress,
    getPropertyValue,
    postPropertyValue,
    getPropertyDescription,
    postPropertyDescription,
    getPropertyCheckYourAnswers
  )

  setup("TransferCashAndOtherAssetsSelection", "Select cash and other assets").withRequests(
    getTypeOfAsset,
    postTypeOfMultipleAssets("[0]", "cashAssets", "[4]", "otherAsset")
  )

  setup("TransferCashDetails", "Enter cash transfer details").withRequests(
    getCashInTransfer,
    postCashInTransfer
  )

  setup("TransferOtherAssetsDetails", "Enter other asset details").withRequests(
    getOtherAssetsStart,
    getOtherAssetsDescription,
    postOtherAssetsDescription,
    getOtherAssetsValue,
    postOtherAssetsValue,
    getOtherAssetsCheckYourAnswers,
    getOtherAssetsAmendContinue,
    postOtherAssetsAmendContinue,
    getCheckYourAnswers
  )

  setup("QROPSBasicDetails", "Enter QROPS basic details").withRequests(
    getQROPSName,
    postQROPSName("LIC"),
    getQROPSRef,
    postQROPSRef("QROPS123456")
  )

  setup("QROPSAddress", "Enter QROPS address").withRequests(
    getQROPSAddress,
    postQROPSAddress("Some Building", "Some Street", "United Kingdom")
  )

  setup("QROPSCountry", "Enter QROPS country").withRequests(
    getQROPSCountry,
    postQROPSCountry("United Kingdom")
  )

  setup("SchemeManagerIndividualDetails", "Enter individual scheme manager details").withRequests(
    getTypeOfSchemeManager,
    postTypeOfSchemeManager("individual"),
    getNameOfSchemeManager,
    postNameOfSchemeManager("First", "Name")
  )

  setup("SchemeManagerOrganisationDetails", "Enter organisation scheme manager details").withRequests(
    getTypeOfSchemeManager,
    postTypeOfSchemeManager("organisation"),
    getNameOfOrganisation,
    postNameOfOrganisation,
    getNameOfOrganisationIndividual,
    postNameOfOrganisationIndividual
  )

  setup("SchemeManagerContactDetails", "Enter scheme manager contact details").withRequests(
    getSchemeManagerAddress,
    postSchemeManagerAddress,
    getSchemeManagerEmail,
    postSchemeManagerEmail,
    getSchemeManagerContact,
    postSchemeManagerContact,
    getSchemeManagerCheckYourAnswers
  )

  runSimulation()
}
