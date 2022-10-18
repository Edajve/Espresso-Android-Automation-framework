package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.steps.Hooks;
import mobile.utils.HelperUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private AndroidDriver driver;

    public LoginPage() {
    }

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"LoginScreen-Email-Input-TextInput, Type Text Here\"]")
    public WebElement emailInputField;

    @AndroidFindBy(accessibility = "LoginScreen-Password-Input-TextInput, Type Text Here")
    public WebElement passInputField;
    //LoginScreen-Login-Button-Touch
    @AndroidFindBy(accessibility = "LoginScreen-Login-Button-Text")
    public WebElement loginButton;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"LoginScreen-Banner-warning-View\"]/android.widget.TextView")
    public WebElement loginErrorMessageText;

    public LoginPage login(String email, String password) {
        HelperUtils.waitForElementVisible(Hooks.androidDriver, emailInputField);
        emailInputField.click();
        emailInputField.sendKeys(email);
        passInputField.click();
        passInputField.sendKeys(password);
        return new LoginPage(Hooks.androidDriver);
    }

    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage(Hooks.androidDriver);
    }

    public WebElement getLoginErrorText(){
        return loginErrorMessageText;
    }
}
