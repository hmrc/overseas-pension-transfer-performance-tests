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

  setup("MemberJourneyIsUkResident", "Member is UK resident").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getMemberName,
    postMemberName("FirstName","LastName"),
    getMemberNino,
    postMemberNino,
    getMemberDOB,
    postMemberDOB,
    getMemberCurrentAddress,
    postMemberCurrentAddress,
    getMemberIsResidentUk,
    getMemberCheckYourAnswers

  )
  setup("MemberJourneyIsNotUkResident", "Member is not a UK resident").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getMemberName,
    postMemberName("FirstName","LastName"),
    getMemberNino,
    postMemberNino,
    getMemberDOB,
    postMemberDOB,
    getMemberCurrentAddress,
    postMemberCurrentAddress,
    getMemberIsResidentUk,
    postMemberIsResidentUk(false),
    getMemberHasEverBeenResidentUk,
    postMemberHasEverBeenResidentUk(false),
    getMemberCheckYourAnswers
  )
  setup("TransferDetailsQuotedShareJourney", "Quoted shares type").withRequests(
      getAuthWizard,
      postLoginAsPspUser(psaid= "A2100005"),
      getHome,
      getDashBoardPage,
      getWhatWillBeNeededPage,
      postWhatWillBeNeededPage,
      getTaskListPage,
      getTypeOfAsset,
      postTypeOfAsset("[2]","quotedShareAssets"),
      getQuotedSharesStart,
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
  setup("TransferDetailsUnquotedShareJourney", "Unquoted shares type").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getTypeOfAsset,
    postTypeOfAsset("[1]","unquotedShareAssets"),
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

  setup("TransferDetailsPropertyJourney", "Property type").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getTypeOfAsset,
    postTypeOfAsset("[3]","propertyAsset"),
    getPropertyStart,
    getPropertyAddress,
    postPropertyAddress,
    getPropertyValue,
    postPropertyValue,
    getPropertyDescription,
    postPropertyDescription,
    getPropertyCheckYourAnswers

  )

  setup("TransferDetailsCashAndOtherAssetsJourney", "Cash and other assets type").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getTypeOfAsset,
    postTypeOfMultipleAssets("[0]","cashAssets","[4]","otherAsset"),
    getCashInTransfer,
    postCashInTransfer,
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

  setup("QROPSJourney", "QROPS Journey").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getQROPSName,
    postQROPSName("LIC"),
    getQROPSRef,
    postQROPSRef("QROPS123456"),
    getQROPSAddress,
    postQROPSAddress("Some Building","Some Street", "United Kingdom"),
      getQROPSCountry,
    postQROPSCountry("United Kingdom"),
  )

  setup("SchemeManagerAsIndividualJourney", "Scheme manager as Individual").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getTypeOfSchemeManager,
    postTypeOfSchemeManager("individual"),
    getNameOfSchemeManager,
    postNameOfSchemeManager("First","Name"),
    getSchemeManagerAddress,
    postSchemeManagerAddress,
    getSchemeManagerEmail,
    postSchemeManagerEmail,
    getSchemeManagerContact,
    postSchemeManagerContact,
    getSchemeManagerCheckYourAnswers
  )

  setup("SchemeManagerAsOrganisationJourney", "Scheme manager as Organisation").withRequests(
    getAuthWizard,
    postLoginAsPspUser(psaid= "A2100005"),
    getHome,
    getDashBoardPage,
    getWhatWillBeNeededPage,
    postWhatWillBeNeededPage,
    getTaskListPage,
    getTypeOfSchemeManager,
    postTypeOfSchemeManager("organisation"),
    getNameOfOrganisation,
    postNameOfOrganisation,
    getNameOfOrganisationIndividual,
    postNameOfOrganisationIndividual,
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
