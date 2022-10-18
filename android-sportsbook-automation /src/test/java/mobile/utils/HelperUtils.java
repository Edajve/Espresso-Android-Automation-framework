package mobile.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import mobile.steps.Hooks;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class HelperUtils {

    static int waitDuration = Integer.parseInt(ConfigReader.getProperty("waitDuration"));

    public static void tapByElement(AndroidDriver driver, WebElement androidElement) {
        touchAction(driver).tap(TapOptions.tapOptions().withElement(element(androidElement))).perform();
    }

    public static void waitForElementVisible(AndroidDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(Hooks.androidDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void tapByTextElement(AndroidDriver driver, String visibleText) {
        Hooks.androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + visibleText + "\")")).click();
    }

    public static TouchAction touchAction(AndroidDriver driver) {
        return new TouchAction(driver);
    }

    public static WebElement elementByText(AndroidDriver driver, String visibleText) {
        Hooks.androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + visibleText + "\")"));
        return null;
    }

    public static void scrollToElement(AndroidDriver driver, String visibleText) {
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector())" + ".scrollIntoView(text(\"" + visibleText + "\"))");
    }

    public static void scrollAndClick(String visibleText) {
        //Hooks.androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
    }

    public static String fuboEmailGenerator(String initialEmail) {
        StringBuilder currentNumbers = new StringBuilder();
        try {
            String firstHalf = initialEmail.trim().substring(0, 7);
            String lastHalf = initialEmail.trim().substring(7);
            Random rand = new Random();
            for (int i = 0; i < 6; i++) {
                int randomInt = rand.nextInt(10);
                currentNumbers.append(randomInt);
            }
            return firstHalf + "+" + currentNumbers + lastHalf;
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static String fuboSBPasswordGenerator() {
        String frontHalfOfPassword = "FuboTV#";

        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //The Testing env is using london server time for the passwords
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String londonDate = dateFormat.format(today);
        return frontHalfOfPassword + londonDate;
    }

    public static StringBuilder generatePhoneNumber() {
        StringBuilder currentNumbers = new StringBuilder();
        StringBuilder phoneNumber = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int randomInt = rand.nextInt(10);
            currentNumbers.append(randomInt);
            phoneNumber.append(currentNumbers);
        }
        return phoneNumber;
    }

    public static String generateFourSSN() {
        StringBuilder sSN = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            String randomInt = Integer.toString(rand.nextInt(10));
            sSN.append(randomInt);
        }
        return sSN.toString();
    }

    private final static PointerInput FINGER = new PointerInput(TOUCH, "finger");

    /**
            //Use this to scroll down
            Dimension dimension = Hooks.androidDriver.manage().window().getSize();
            Point start = new Point((int)(dimension.width*0.5), (int)(dimension.height*0.9));
            Point end = new Point((int)(dimension.width*0.2), (int)(dimension.height*0.1));
            HelperUtils.doSwipe(Hooks.androidDriver, start, end, 1000);  //with duration 1s

            Thread.sleep(3000);

            //Use this to scroll up
            Point start = new Point((int)(dimension.width*0.2), (int)(dimension.height*0.2));
            Point end = new Point((int)(dimension.width*0.5), (int)(dimension.height*0.8));
            HelperUtils.doSwipe(Hooks.androidDriver, start, end, 1000); //with duration 1s
     */
    public static void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY()))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public static void scroll(String scrollDistance) {
        Dimension dimension = Hooks.androidDriver.manage().window().getSize();
        switch (scrollDistance) {
            case "up":
                Point startt = new Point((int)(dimension.width*0.2), (int)(dimension.height*0.2));
                Point endd = new Point((int)(dimension.width*0.5), (int)(dimension.height*0.8));
                HelperUtils.doSwipe(Hooks.androidDriver, startt, endd, 1000);
                break;
            case "extra small":

                Point start = new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.9));
                Point end = new Point((int) (dimension.width * 0.2), (int) (dimension.height * 0.9));
                HelperUtils.doSwipe(Hooks.androidDriver, start, end, 1000);
                break;
            case "small":
                Point startSmall = new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.9));
                Point endSmall = new Point((int) (dimension.width * 0.2), (int) (dimension.height * 0.6));
                HelperUtils.doSwipe(Hooks.androidDriver, startSmall, endSmall, 1000);
                break;
            case "long":
                Point startOne = new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.9));
                Point endOne = new Point((int) (dimension.width * 0.2), (int) (dimension.height * 0.1));
                HelperUtils.doSwipe(Hooks.androidDriver, startOne, endOne, 1000);
                break;
        }
    }
}