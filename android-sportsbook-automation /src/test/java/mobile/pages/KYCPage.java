package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.steps.Hooks;
import mobile.utils.HelperUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class KYCPage {

    private AndroidDriver driver;

    public KYCPage() {
    }

    public KYCPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "KYCScreen-input-firstName-TextInput, Type Text Here")
    public WebElement firstName;

    @AndroidFindBy(accessibility = "KYCScreen-input-lastName-TextInput, Type Text Here")
    public WebElement lastName;

    @AndroidFindBy(
            xpath = "//android.view.ViewGroup[@content-desc=\"" +
                    "KYCScreen-date-month-dropdown-View\"]" +
                    "/android.view.ViewGroup/android.widget.EditText")
    public WebElement monthDropDown;

    @AndroidFindBy(
            xpath = "//android.view.ViewGroup[@content-desc=\"" +
                    "KYCScreen-date-day-dropdown-View\"]" +
                    "/android.view.ViewGroup/android.widget.EditText")
    public WebElement dayDropDown;

    @AndroidFindBy(
            xpath = "//android.view.ViewGroup[@content-desc=\"" +
                    "KYCScreen-date-year-dropdown-View\"]" +
                    "/android.view.ViewGroup/android.widget.EditText")
    public WebElement yearDropDown;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout" +
            "/android.widget.LinearLayout/android.widget.FrameLayou" +
            "t/android.widget.FrameLayout/android.widget.FrameLayout" +
            "/androidx.appcompat.widget.LinearLayoutCompat/android." +
            "widget.FrameLayout/android.widget.ListView/android.widge" +
            "t.CheckedTextView[6]")
    public WebElement year1983;

    @AndroidFindBy(accessibility = "KYCScreen-input-address-TextInput, Type Text Here")
    public WebElement houseNumber;

    @AndroidFindBy(accessibility = "KYCScreen-input-address2-TextInput, Type Text Here")
    public WebElement streetName;

    @AndroidFindBy(accessibility = "KYCScreen-input-city-TextInput, Type Text Here")
    public WebElement city;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"" +
            "KYCScreen-dropdown-state-View\"]/android.view.ViewGroup/android.widget.EditText")
    public WebElement stateDropDown;

    @AndroidFindBy(accessibility = "KYCScreen-input-zipcode-TextInput, Type Text Here")
    public WebElement zipCode;

    @AndroidFindBy(accessibility = "KYCScreen-telephone-TextInput-TextInput, Type Text Here")
    public WebElement phoneNumber;

    @AndroidFindBy(accessibility = "KYCScreen-socialSecurity-TextInput-TextInput, Type Text Here")
    public WebElement ssn;

    @AndroidFindBy(accessibility = "Toggle-Switch, Click to toggle")
    public WebElement twoFactorCheckbox;

    @AndroidFindBy(accessibility = "KYCScreen-input-promoCode-TextInput, Type Text Here")
    public WebElement promoCode;

    @AndroidFindBy(accessibility = "KYCScreen-checkbox-TCCheckbox-Touch")
    public WebElement tcCheckbox;

    @AndroidFindBy(accessibility = "KYCScreen-checkbox-ageCheckbox-Touch")
    public WebElement ageCheckBox;

    @AndroidFindBy(accessibility = "KYCScreen-checkbox-accurateCheckbox-Touch")
    public WebElement informationAccurateCheckbox;

    @AndroidFindBy(accessibility = "KYCScreen-Submit-Button-Touch")
    public WebElement verifyInformationButton;

    @AndroidFindBy(accessibility = "KYCScreen-Error-Label")
    public WebElement kycErrorMessage;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"FormSection-Children\"]" +
            "/android.widget.TextView[2]")
    public WebElement informationIsAccurateTextInCheckBox;

    public VerificationPage completeKYC(
            String first_name,
            String last_name,
            String date_of_birth,
            String house_number,
            String street_name,
            String city,
            String state,
            String zip_code,
            String promotional_code
    ) throws InterruptedException {
        String secondCheckBoxText = "I am not a casino key employee" +
                " or casino employee prohibited from wagering in any " +
                "casino or simulcasting facility in the State of New Jersey," +
                " nor am I one of the following persons prohibited from wagering" +
                " in New Jersey: A prohibited sports pool participant," +
                " an employee of a sports governing body or member team " +
                "who is prohibited from wagering, or the direct or indirect" +
                " legal or beneficial owner of 10 percent or more of a sports" +
                " governing body or any of its member teams. If I am an " +
                "employee of a sports governing body or one of its member" +
                " teams and am not a prohibited sports pool participant," +
                " I have completed this form.";

        String lastCheckBox = "I am not on the New Jersey statewide " +
                "self-exclusion list nor otherwise prohibited from " +
                "participating in sports wagering in the State of New Jersey.";

        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);

        monthDropDown.click();
        HelperUtils.tapByTextElement(Hooks.androidDriver, "March");

        HelperUtils.waitForElementVisible(Hooks.androidDriver, dayDropDown);
        dayDropDown.click();
        HelperUtils.tapByTextElement(Hooks.androidDriver, date_of_birth.substring(2, 4));

        HelperUtils.waitForElementVisible(Hooks.androidDriver, yearDropDown);

        yearDropDown.click();
        //scroll down twice to year 1983
        Thread.sleep(500);
        HelperUtils.scroll("long");
        HelperUtils.scroll("long");
        HelperUtils.tapByTextElement(Hooks.androidDriver, date_of_birth.substring(5));


        houseNumber.sendKeys(house_number);
        streetName.sendKeys(street_name);

        HelperUtils.scroll("small");

//        HelperUtils.scroll("small");

        this.city.sendKeys(city);

        stateDropDown.click();
        Thread.sleep(1000);
        HelperUtils.scroll("small");
        HelperUtils.tapByTextElement(Hooks.androidDriver, state);


        Thread.sleep(500);
        HelperUtils.scroll("small");
        zipCode.sendKeys(zip_code);

        phoneNumber.sendKeys(HelperUtils.generatePhoneNumber());
        ssn.sendKeys(HelperUtils.generateFourSSN());

        Thread.sleep(500);
        HelperUtils.scroll("long");

        //promoCode.sendKeys("");

        tcCheckbox.click();
        ageCheckBox.click();
        informationAccurateCheckbox.click();

        HelperUtils.scroll("long");

        HelperUtils.scrollToElement(Hooks.androidDriver, lastCheckBox);
        verifyInformationButton.click();
        return new VerificationPage(Hooks.androidDriver);
    }

    public VerificationPage completeKycWithInvalidDOB(
            String first_name,
            String last_name,
            String date_of_birth,
            String house_number,
            String street_name,
            String city,
            String state,
            String zip_code,
            String promotional_code
    ) throws InterruptedException {
        String secondCheckBoxText = "I am not a casino key employee" +
                " or casino employee prohibited from wagering in any " +
                "casino or simulcasting facility in the State of New Jersey," +
                " nor am I one of the following persons prohibited from wagering" +
                " in New Jersey: A prohibited sports pool participant," +
                " an employee of a sports governing body or member team " +
                "who is prohibited from wagering, or the direct or indirect" +
                " legal or beneficial owner of 10 percent or more of a sports" +
                " governing body or any of its member teams. If I am an " +
                "employee of a sports governing body or one of its member" +
                " teams and am not a prohibited sports pool participant," +
                " I have completed this form.";

        String lastCheckBox = "I am not on the New Jersey statewide " +
                "self-exclusion list nor otherwise prohibited from " +
                "participating in sports wagering in the State of New Jersey.";

        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);

        monthDropDown.click();
        HelperUtils.tapByTextElement(Hooks.androidDriver, "March");

        HelperUtils.waitForElementVisible(Hooks.androidDriver, dayDropDown);
        dayDropDown.click();
        HelperUtils.tapByTextElement(Hooks.androidDriver, date_of_birth.substring(2, 4));

        HelperUtils.waitForElementVisible(Hooks.androidDriver, yearDropDown);

        yearDropDown.click();
        //scroll down twice to year 1983
        Thread.sleep(500);
        HelperUtils.scroll("small");
        HelperUtils.tapByTextElement(Hooks.androidDriver, date_of_birth.substring(5));

        houseNumber.sendKeys(house_number);
        streetName.sendKeys(street_name);

        HelperUtils.scroll("small");

//        HelperUtils.scroll("small");

        this.city.sendKeys(city);

        stateDropDown.click();
        Thread.sleep(1000);
        HelperUtils.scroll("small");
        HelperUtils.tapByTextElement(Hooks.androidDriver, state);


        Thread.sleep(500);
        HelperUtils.scroll("small");
        zipCode.sendKeys(zip_code);

        phoneNumber.sendKeys(HelperUtils.generatePhoneNumber());
        ssn.sendKeys(HelperUtils.generateFourSSN());

        Thread.sleep(500);
        HelperUtils.scroll("long");

        //promoCode.sendKeys("");

        tcCheckbox.click();
        ageCheckBox.click();
        informationAccurateCheckbox.click();

        HelperUtils.scroll("long");

        return new VerificationPage(Hooks.androidDriver);
    }

    public String getKycErrorMessage() {
        return kycErrorMessage.getText();
    }

    public VerificationPage completeKycWithExistingNumber(
            String first_name,
            String last_name,
            String date_of_birth,
            String house_number,
            String street_name,
            String city,
            String state,
            String zip_code,
            String promotional_code
    ) throws InterruptedException {

        String secondCheckBoxText = "I am not a casino key employee" +
                " or casino employee prohibited from wagering in any " +
                "casino or simulcasting facility in the State of New Jersey," +
                " nor am I one of the following persons prohibited from wagering" +
                " in New Jersey: A prohibited sports pool participant," +
                " an employee of a sports governing body or member team " +
                "who is prohibited from wagering, or the direct or indirect" +
                " legal or beneficial owner of 10 percent or more of a sports" +
                " governing body or any of its member teams. If I am an " +
                "employee of a sports governing body or one of its member" +
                " teams and am not a prohibited sports pool participant," +
                " I have completed this form.";

        String lastCheckBox = "I am not on the New Jersey statewide " +
                "self-exclusion list nor otherwise prohibited from " +
                "participating in sports wagering in the State of New Jersey.";

        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);

        monthDropDown.click();
        HelperUtils.tapByTextElement(Hooks.androidDriver, "March");

        HelperUtils.waitForElementVisible(Hooks.androidDriver, dayDropDown);
        dayDropDown.click();
        HelperUtils.tapByTextElement(Hooks.androidDriver, date_of_birth.substring(2, 4));

        HelperUtils.waitForElementVisible(Hooks.androidDriver, yearDropDown);

        yearDropDown.click();
        //scroll down twice to year 1983
        Thread.sleep(500);
        HelperUtils.scroll("long");
        HelperUtils.scroll("long");
        HelperUtils.tapByTextElement(Hooks.androidDriver, date_of_birth.substring(5));


        houseNumber.sendKeys(house_number);
        streetName.sendKeys(street_name);

        HelperUtils.scroll("small");

        this.city.sendKeys(city);

        stateDropDown.click();
        Thread.sleep(1000);
        HelperUtils.scroll("small");
        HelperUtils.tapByTextElement(Hooks.androidDriver, state);


        Thread.sleep(500);
        HelperUtils.scroll("small");
        zipCode.sendKeys(zip_code);

        phoneNumber.sendKeys("7089962138");
        ssn.sendKeys(HelperUtils.generateFourSSN());

        Thread.sleep(500);
        HelperUtils.scroll("long");

        //promoCode.sendKeys("");

        tcCheckbox.click();
        ageCheckBox.click();
        informationAccurateCheckbox.click();

        HelperUtils.scroll("long");

        HelperUtils.scrollToElement(Hooks.androidDriver, lastCheckBox);
        verifyInformationButton.click();
        return new VerificationPage(Hooks.androidDriver);

    }

}
