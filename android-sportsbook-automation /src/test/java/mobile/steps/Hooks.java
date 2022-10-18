package mobile.steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import mobile.utils.ConfigReader;
import mobile.utils.DesiredCapabilitiesUtils;


public class Hooks {
    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;


    @Before
    public void setup() throws Exception {
        switch (ConfigReader.getProperty("device")) {
            case "ios":
                iosDriver = DesiredCapabilitiesUtils.setupIOSDesiredCapabilities();
                break;
            case "android":
                androidDriver = DesiredCapabilitiesUtils.setupAndroidDesiredCapabilities();
                break;
            default:
                throw new Exception("No setup found for device!");
        }
    }

    @After
    public void tearDown() throws Exception {
        switch (ConfigReader.getProperty("device")) {
            case "ios":
                iosDriver.quit();
                break;
            case "android":
                androidDriver.quit();
                break;
            default:
                throw new Exception("No setup found for device!");
        }
    }
}
