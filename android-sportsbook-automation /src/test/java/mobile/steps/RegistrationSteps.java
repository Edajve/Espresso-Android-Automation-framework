package mobile.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import mobile.pages.*;
import mobile.utils.HelperUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class RegistrationSteps {
    HomePage homePage = new HomePage();
    NewOrOldUserPage newOrOldUserPage = new NewOrOldUserPage(Hooks.androidDriver);
    StateToBetInPage chooseState = new StateToBetInPage(Hooks.androidDriver);
    SignUpPage signUpPage = new SignUpPage(Hooks.androidDriver);
    KYCPage kycPage = new KYCPage(Hooks.androidDriver);
    VerificationPage verificationPage = new VerificationPage(Hooks.androidDriver);

    @Given("^user signs up as a new user using this information$")
    public void user_signs_up_as_a_new_using_this_information(DataTable signUpData) throws Exception {
        homePage.clickJoinButton();
        newOrOldUserPage.clickNewUser();
        Thread.sleep(400);
        chooseState.chooseStateToBetIn("New Jersey");

        List<Map<String, String>> dataMap = signUpData.asMaps(String.class, String.class);
        signUpPage.signUpToSportsbook(
                HelperUtils.fuboEmailGenerator(dataMap.get(0).get("Email")),
                HelperUtils.fuboSBPasswordGenerator(),
                dataMap.get(0).get("Zip Code")
        );
        signUpPage.clickSignUpSubmitButton();
    }

    @Given("^completes KYC with this information$")
    public void completes_KYC_with_this_information(DataTable kycData) throws Exception {
        //datable coming from the feature file
        List<Map<String, String>> kycDataMap = kycData.asMaps(String.class, String.class);

        kycPage.completeKYC(
                kycDataMap.get(0).get("First Name"),
                kycDataMap.get(0).get("Last Name"),
                kycDataMap.get(0).get("Date Of Birth"),
                kycDataMap.get(0).get("House Number"),
                kycDataMap.get(0).get("Street Name"),
                kycDataMap.get(0).get("City"),
                kycDataMap.get(0).get("State"),
                kycDataMap.get(0).get("Zip Code"),
                kycDataMap.get(0).get("Promotional Code")
        );
    }

    @Then("^the user should be registered but not verified to the sportsbook$")
    public void the_user_should_be_registered_but_not_verified_to_the_sportsbook() throws Exception {
        Thread.sleep(4000);
        HelperUtils.waitForElementVisible(Hooks.androidDriver, verificationPage.afterKYCSubmissionText);
        Assert.assertEquals("We couldn't verify you", verificationPage.getKycVerificationText());
    }

    @Given("completes KYC with date of birth information under twenty-one")
    public void completes_kyc_with_date_of_birth_information_under_twenty_one(io.cucumber.datatable.DataTable kycData) throws InterruptedException {
        //datable coming from the feature file
        List<Map<String, String>> kycDataMap = kycData.asMaps(String.class, String.class);

        kycPage.completeKycWithInvalidDOB(
                kycDataMap.get(0).get("First Name"),
                kycDataMap.get(0).get("Last Name"),
                kycDataMap.get(0).get("Date Of Birth"),
                kycDataMap.get(0).get("House Number"),
                kycDataMap.get(0).get("Street Name"),
                kycDataMap.get(0).get("City"),
                kycDataMap.get(0).get("State"),
                kycDataMap.get(0).get("Zip Code"),
                kycDataMap.get(0).get("Promotional Code")
        );
    }

    @Then("the sportsbook should give a {string} error message and not allowed to sign up to the sportsbook")
    public void theSportsbookShouldGiveAErrorMessageAndNotAllowedToSignUpToTheSportsbook(String kycErrorMessage) throws InterruptedException {
        HelperUtils.waitForElementVisible(Hooks.androidDriver, verificationPage.afterKYCSubmissionText);
        Assert.assertEquals(kycErrorMessage, verificationPage.getKycVerificationText());
    }

    @Given("user attempts signs up with an already registered account")
    public void user_attempts_signs_up_with_an_already_registered_account(io.cucumber.datatable.DataTable signUpData) throws Exception {
        homePage.clickJoinButton();
        newOrOldUserPage.clickNewUser();
        chooseState.chooseStateToBetIn("New Jersey");

        List<Map<String, String>> dataMap = signUpData.asMaps(String.class, String.class);
        signUpPage.signUpToSportsbook(
                dataMap.get(0).get("Email"),
                HelperUtils.fuboSBPasswordGenerator(),
                dataMap.get(0).get("Zip Code")
        );
        signUpPage.clickSignUpSubmitButton();
    }

    @Then("they should not be able to continue registering")
    public void they_should_not_be_able_to_continue_registering() {

    }

    @Then("the sportsbook should give a {string} error message and can not continue to register")
    public void theSportsbookShouldGiveAErrorMessageAndCanNotContinueToRegister(String kycErrorMessage) {
        Assert.assertEquals(kycErrorMessage, signUpPage.getSignUpErrorText());
    }

    @Given("completes KYC with this information with an already existing phone number")
    public void completes_kyc_with_this_information_with_an_already_existing_phone_number(io.cucumber.datatable.DataTable kycData) throws InterruptedException {
        //datable coming from the feature file
        List<Map<String, String>> kycDataMap = kycData.asMaps(String.class, String.class);

        kycPage.completeKycWithExistingNumber(
                kycDataMap.get(0).get("First Name"),
                kycDataMap.get(0).get("Last Name"),
                kycDataMap.get(0).get("Date Of Birth"),
                kycDataMap.get(0).get("House Number"),
                kycDataMap.get(0).get("Street Name"),
                kycDataMap.get(0).get("City"),
                kycDataMap.get(0).get("State"),
                kycDataMap.get(0).get("Zip Code"),
                kycDataMap.get(0).get("Promotional Code")
        );
    }

}