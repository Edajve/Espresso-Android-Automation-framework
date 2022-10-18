package mobile.steps;


import io.cucumber.java.en.Given;
import mobile.utils.ConfigReader;
import mobile.utils.HelperUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileCommonSteps {
    @Given("^user opens Android Sportsbook app$")
    public void userOpensAndroidSportsbookApp() throws InterruptedException {
        switch (ConfigReader.getProperty("device")) {
            case "ios":
                HelperUtils.tapByTextElement(Hooks.androidDriver, "Allow");
                break;
            case "android":
                HelperUtils.tapByElement(Hooks.androidDriver, (WebElement) Hooks.androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]")));
                Thread.sleep(5000);
                break;
        }
    }
}
