package mobile.pages;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en_scouse.An;
import mobile.steps.Hooks;
import mobile.utils.HelperUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    private AndroidDriver driver;

    public SignUpPage() {
    }

    public SignUpPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "SignUpScreen-Email-Input-TextInput, Type Text Here")
    public WebElement emailAddressInputBox;

    @AndroidFindBy(accessibility = "SignUpScreen-Password-TextInput, Type Text Here")
    public WebElement passwordInputBox;

    @AndroidFindBy(accessibility = "SignUpScreen-input-zipcode-TextInput, Type Text Here")
    public WebElement zipCodeInputField;

    @AndroidFindBy(accessibility = "SignUpScreen-CreateAccount-Touch")
    public WebElement createAccountButton;

    @AndroidFindBy(accessibility = "KYCScreen-Error-Label")
    public WebElement signUpErrorText;

    public SignUpPage signUpToSportsbook(String email, String pass, String zip) {
        HelperUtils.waitForElementVisible(Hooks.androidDriver, emailAddressInputBox);
        emailAddressInputBox.sendKeys(email);

        passwordInputBox.sendKeys(HelperUtils.fuboSBPasswordGenerator());

        zipCodeInputField.clear();
        zipCodeInputField.sendKeys(zip);

        Hooks.androidDriver.hideKeyboard();
        return new SignUpPage(Hooks.androidDriver);
    }

    public KYCPage clickSignUpSubmitButton() {
        createAccountButton.click();
        return new KYCPage(Hooks.androidDriver);
    }

    public String getSignUpErrorText() {
        return signUpErrorText.getText();
    }
}
