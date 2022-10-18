package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.steps.Hooks;
import mobile.utils.HelperUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewOrOldUserPage {

    StateToBetInPage stateToBetInPage = new StateToBetInPage(Hooks.androidDriver);

    private AndroidDriver driver;

    public NewOrOldUserPage() {
    }

    public NewOrOldUserPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "LandingScreen-SignUp-Button-Touch")
    public WebElement newUserButton;

    public StateToBetInPage clickNewUser() {
        HelperUtils.tapByTextElement(Hooks.androidDriver, "Create an Account");
        return new StateToBetInPage(Hooks.androidDriver);
    }
}
