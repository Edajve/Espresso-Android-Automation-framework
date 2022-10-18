package mobile.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mobile.pages.HomePage;
import mobile.pages.LoginPage;
import mobile.utils.ConfigReader;
import mobile.utils.HelperUtils;
import org.junit.Assert;

public class LoginSteps {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage(Hooks.androidDriver);

    @When("^user logs in with \"([^\"]*)\"$")
    public void userLogsInWith(String typeOfCredentials) throws Throwable {

        switch (typeOfCredentials) {
            case "correct credentials":
                homePage.clickLoginButton();
                loginPage.login
                        (ConfigReader.getProperty("correctEmail"),
                                ConfigReader.getProperty("correctPassword"));
                loginPage.clickLoginButton();
                break;
            case "incorrect credentials":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("incorrectEmailAddress"), ConfigReader.getProperty("incorrectPassword"));
                loginPage.clickLoginButton();
                break;
            case "first time deposit account":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("newUserEmailAddress"), ConfigReader.getProperty("newUserPassword"));
                loginPage.clickLoginButton();
                break;
            case "a account that has daily deposit limit set":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("emailWIthDailyLimitSet"), ConfigReader.getProperty("passwordWithDailyLimitSet"));
                loginPage.clickLoginButton();
                break;
            case "a account that has weekly deposit limit set":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("emailWithWeeklyDepositLimit"), ConfigReader.getProperty("passwordWithWeeklyDepositLimit"));
                loginPage.clickLoginButton();
                break;
            case "a account that has monthly deposit limit set":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("emailWithMonthlyDepositLimit"), ConfigReader.getProperty("passwordWithMonthlyDepositLimit"));
                loginPage.clickLoginButton();
                break;
            case "suspended account":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("amelcoSuspendedEmail"), ConfigReader.getProperty("amelcoSuspendedPass"));
                loginPage.clickLoginButton();
                break;
            case "deactivated account":
                homePage.clickLoginButton();
                loginPage.login(ConfigReader.getProperty("deactivatedSportsbookEmail"), ConfigReader.getProperty("deactivatedSportsbookPassword"));
                loginPage.clickLoginButton();
                break;
            case "just an email":
                homePage.clickLoginButton();
                loginPage.login("justEmail@fubo.tv", "");
                break;
            case "just an password":
                homePage.clickLoginButton();
                loginPage.login("", "FuboTV#20221007");
                break;
            case "a TV account":
                homePage.clickJoinButton();
                HelperUtils.tapByTextElement(Hooks.androidDriver, "Log in");
                loginPage.login(ConfigReader.getProperty("QAaFuboTvAccountEmail"), ConfigReader.getProperty("QAFuboTvAccountPassword"));
                loginPage.clickLoginButton();
                break;
        }
    }

    @Then("^user should be logged into their account$")
    public void userShouldBeLoggedIntoTheirAccount() throws InterruptedException {
        //We need a developer to add an id to the green deposit button for verification
        Thread.sleep(4000);
    }

    @Then("user should receive an login error message of {string} not allowing them to login")
    public void userShouldReceiveAnLoginErrorMessageOfNotAllowingThemToLogin(String errorMessage) throws InterruptedException {
        Thread.sleep(4000);
        HelperUtils.waitForElementVisible(Hooks.androidDriver, loginPage.getLoginErrorText());
        Assert.assertEquals(errorMessage, loginPage.getLoginErrorText().getText());
    }

    @Then("the login button should not be clickable")
    public void theLoginButtonShouldNotBeClickable() throws InterruptedException {
        Thread.sleep(500);
        Assert.assertTrue(loginPage.loginButton.isEnabled());
    }
}