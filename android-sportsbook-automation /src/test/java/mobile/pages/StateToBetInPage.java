package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.steps.Hooks;
import mobile.utils.HelperUtils;
import org.openqa.selenium.support.PageFactory;

public class StateToBetInPage {
    KYCPage kycPage = new KYCPage(Hooks.androidDriver);

    private AndroidDriver driver;

    public StateToBetInPage() {
    }

    public StateToBetInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public SignUpPage chooseStateToBetIn(String state) throws Exception {
        Thread.sleep(1000);
        switch (state) {
            case "New Jersey":
                HelperUtils.tapByTextElement(Hooks.androidDriver, "New Jersey");
                break;
            case "Indiana":
                HelperUtils.tapByTextElement(Hooks.androidDriver, "Indiana");
                break;
            default:
                throw new Exception("No state was selected!");
        }
        return new SignUpPage(Hooks.androidDriver);
    }
}
