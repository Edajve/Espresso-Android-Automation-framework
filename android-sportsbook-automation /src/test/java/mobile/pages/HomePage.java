package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.steps.Hooks;
import mobile.utils.HelperUtils;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage {

    private AndroidDriver driver;

    public HomePage() {
    }

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    public HomePage validateLastLoginText() throws InterruptedException {

        return new HomePage(driver);
    }

    public LoginPage clickLoginButton() {
        HelperUtils.tapByTextElement(Hooks.androidDriver, "Login");
        return new LoginPage(Hooks.androidDriver);
    }

    public LoginPage clickJoinButton() {
        HelperUtils.tapByTextElement(Hooks.androidDriver, "Join");
        return new LoginPage(Hooks.androidDriver);
    }
}
