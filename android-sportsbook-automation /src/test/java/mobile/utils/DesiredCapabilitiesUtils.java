package mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesiredCapabilitiesUtils {

    public static AndroidDriver setupAndroidDesiredCapabilities() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", ConfigReader.getProperty("androidDeviceName"));
        desiredCapabilities.setCapability("platformName", ConfigReader.getProperty("androidPlatformName"));
        desiredCapabilities.setCapability("appium:appPackage", ConfigReader.getProperty("appPackage"));
        desiredCapabilities.setCapability("appium:appActivity", ConfigReader.getProperty("appActivity"));

        URL appiumServerUrl = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(appiumServerUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.getProperty("timeoutForImplicitWait")), TimeUnit.SECONDS);
        return driver;
    }

    public static IOSDriver setupIOSDesiredCapabilities() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", ConfigReader.getProperty("iosDeviceName"));
        desiredCapabilities.setCapability("platformName", ConfigReader.getProperty("iosPlatformName"));
        desiredCapabilities.setCapability("appium:platformVersion", ConfigReader.getProperty("iosPlatformVersion"));
        desiredCapabilities.setCapability("appium:udid", ConfigReader.getProperty("udid"));
        desiredCapabilities.setCapability("appium:appPath", ConfigReader.getProperty("iosAppPath"));

        URL appiumServerUrl = new URL("http://localhost:4723/wd/hub");
        IOSDriver driver = new IOSDriver(appiumServerUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.getProperty("timeoutForImplicitWait")), TimeUnit.SECONDS);
        return driver;
    }
}
