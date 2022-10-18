
# sportsbook ui-automation framework

BDD-Framework using Java for programming language,
Selenium for automation framework in addition with Cucumber for feature files
and Gherkin for BDD language,
Appium for mobile testing,
and Junit for test annotations and asserstions.

Dependencies:
- [cucumber-java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java)
- [cucumber-junit](https://mvnrepository.com/artifact/io.cucumber/cucumber-junit)
- [selenium-java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- [webdrivermanager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager)
- [cucumber-core](https://mvnrepository.com/artifact/io.cucumber/cucumber-core)

# Local  Environment Setup


This documents details how to set-up a local environment for QA Engineers

## Universal environment preparation

### Prerequisites

This should be done after onboarding process has been completed, you should have
- Working Android emulator with Fubo sportsbook app able to be launched
- Working IOS emulator with Fubo sportsbook app able to be launched

#### Installing Java and Java Plugins

1. Install [Java JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
2. Install [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac)
3. Open IntelliJ, in the top left corner click IntelliJ IDEA > Prefrences > Plugins
4. Install two additional plugins Cucumber for Java, Gherkin, Kotlin

#### Cross browser testing (Optional)
The default browser for automation is [Google Chrome](https://www.google.com/chrome/?brand=YTUH&geo=US&gclid=CjwKCAjw7--KBhAMEiwAxfpkWArw7h3Fuo9UXIiwgcidahv7mW9lY_CyDKWA72MvHGj42bU6UiTBDhoCbFoQAvD_BwE&gclsrc=aw.ds) so you will need to have Chrome installed.
For cross browser testing you will need to install the following browsers as well

- [Firefix](https://www.mozilla.org/en-US/firefox/new/)
- [Edge](https://www.microsoft.com/en-us/edge?brand=M022&OCID=AID2200279_SEM_CjwKCAjw7--KBhAMEiwAxfpkWKR2yMJIA7F8QFsaR8Is9IDoFFYtv_VO2wH8LwhAD0bmilZ-P6CzmhoC-mgQAvD_BwE:G:s&ef_id=CjwKCAjw7--KBhAMEiwAxfpkWKR2yMJIA7F8QFsaR8Is9IDoFFYtv_VO2wH8LwhAD0bmilZ-P6CzmhoC-mgQAvD_BwE:G:s&gclid=CjwKCAjw7--KBhAMEiwAxfpkWKR2yMJIA7F8QFsaR8Is9IDoFFYtv_VO2wH8LwhAD0bmilZ-P6CzmhoC-mgQAvD_BwE)

## Mobile

### Installing and setting up Appium

Appium is a tool for running scripts and testing native applications, we will
need Appium to locate mobile elements. We will need to set IOS and Android
devices to Appium.

- Download Appium [Version 1.15.0](https://github.com/appium/appium-desktop/releases/tag/v1.15.0)

### Setting up Devices to Appium server

To connect mobile device to Appium you need to provide the desired capabilities.
[Desired Capabilities](https://www.browserstack.com/guide/desired-capabilities-in-appium)
in Appium refer to the combination of key-value pairs encoded
in a JSON object. These key-value pairs are defined by the QAs to request the
Appium server for the desired test automation session.

#### Desired capabilities for Android


1. Open Appium and click Start server
2. Click magnifying glass in the top right corner
3. For Android set these capabilities according to you device
- device name
- platform name
4. Save desired capability
   Ex:

```json
{
    "deviceName": "Pixel 5 API 29",
  "platformName": "android"
}
```

#### Desired capabilities for IOS

1. Open Appium and click Start server
2. Click magnifying glass in the top right corner
3. For IOS set these capabilities according to you device
- device name
- platform name
- platform Version
- udid

  -From Xcode: Window -> Devices and Simulators
  -> Simulators. The Identifier value is the UDID.
- app location
4. Save desired capability

Ex:

 ```json
 {
"deviceName": "iPhone 12 mini",
  "platformName": "ios",
  "platformVersion": "14.3",
  "udid": "C8B07FF9-75C1-40E1-A6A8-C85DD6A53812",
  "app": "/Users/dajveechols/Library/Developer/Xcode/DerivedData/SportsApp-bzqdueeyszvapdailsuxbhjdxcjo/Build/Products/Debug-iphonesimulator/SportsApp.app"
 }
 ```
### Using Appium

After desired capabilities are configured and saved. Run IOS device through Xcode
or run Android device through Visual Studio Code.
On Appium, select corresponding device and click Start Session.
Virtual devices screen should be shown on Appiums window.

You are all connected!

P.S. Appium can only connect with one device at a time,
so Appium should be connected with whichever device is being tested at the time.  


