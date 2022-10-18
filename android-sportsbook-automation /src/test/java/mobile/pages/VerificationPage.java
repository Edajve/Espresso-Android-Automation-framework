package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class VerificationPage {
    private AndroidDriver driver;

    public VerificationPage() {
    }

    public VerificationPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android." +
            "widget.LinearLayout/android.widget.FrameLayout/android.widget." +
            "FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android" +
            ".view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/" +
            "android.view.ViewGroup/android.widget.ScrollView/android.view." +
            "ViewGroup/android.view.ViewGroup/android.widget.TextView[1]")
    public WebElement afterKYCSubmissionText;


    public String getKycVerificationText() {
        return afterKYCSubmissionText.getText();
    }
}
