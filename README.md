# Appium v7-to-v8-migration Guide
  
  **Appium v7-to-v8 migration Guide**

Much waited Selenium 4 has been released on September,2021 with a bunch of new features and they remove support for the legacy protocol(JWP) and use the W3C WebDriver standard by default under the hood. Since that appium has been working to get a seamless experience of Selenium 4. Beta version of Appium client were updated by upgrading the selenium dependency but there were lots of glitches. Now they have launched the stable version with fixes, but still there are some issues present. Hoping they will fix those issues soon. Let talk's about the changes happened due to this upgradation. 

**W3C specification compatibility :**

1. Java client now supports Selenium 4, which also means it is strictly W3C compliant. It won’t possible to use OLD JWP-based server with new version. 
2. The recommended way to provide capabilities for driver creation is to use specific option builders inherited from BaseOption Class.
**Examples :**
 XCUITestOptions to create a XCUITest driver instance or UiAutomator2Options to create an UiAutomator2 driver instance.
 
 #### Old way to use capabilities 
 
 #### JSON Wire Protocol
 ```java   
  DesiredCapabilities capabilities = new DesiredCapabilities();
  
    capabilities.setCapability("platformName", "android");
    capabilities.setCapability("deviceName", "deviceName");
    capabilities.setCapability("appPackage", "appPackage");
    capabilities.setCapability("appActivity", "appActivity");
    capabilities.setCapability("automationName", "UIAutomator2");
    capabilities.setCapability("noReset", true);
    capabilities.setCapability("unicodeKeyboard", "true");
  ```

 
#### New Way to use capabilities
#### W3C Protocol
```java 
  DesiredCapabilities capabilities = new DesiredCapabilities();
  
  capabilities.setCapability("platformName", "android");
  capabilities.setCapability("appium:deviceName", "deviceName");
  capabilities.setCapability("appium:appPackage", "appPackage");
  capabilities.setCapability("appium:appActivity", "appActivity");
  capabilities.setCapability("appium:automationName", "UIAutomator2");
  capabilities.setCapability("appium:noReset", true);
  capabilities.setCapability("appium:unicodeKeyboard", "true");
 ```
 #### Or
 ```Java
 UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("deviceName").setAppPackage("appPackage")
                .setAppActivity("appActivity")
                .setUnlockKey("unlockKey")
                .setNoReset(true)
                .eventTimings();
``` 
#### Explanation of Code
1. "Appium:" tag have to use with capability name to tell server specifically that these are appium capabilities. Otherwise users will get warning messages like :
 ```Java
Jun 03, 2022 11:28:45 AM org.openqa.selenium.W3CCapabilityKeysValidator validateCapability
WARNING: Support for Legacy Capabilities is deprecated; You are sending "deviceName" which is an invalid capability. Please update to W3C Syntax: https://www.selenium.dev/blog/2022/legacy-protocol-support/
Jun 03, 2022 11:28:45 AM org.openqa.selenium.W3CCapabilityKeysValidator validateCapability
WARNING: Support for Legacy Capabilities is deprecated; You are sending "appPackage" which is an invalid capability. Please update to W3C Syntax: https://www.selenium.dev/blog/2022/legacy-protocol-support/
Jun 03, 2022 11:28:45 AM org.openqa.selenium.W3CCapabilityKeysValidator validateCapability
WARNING: Support for Legacy Capabilities is deprecated; You are sending "appActivity" which is an invalid capability. Please update to W3C Syntax: https://www.selenium.dev/blog/2022/legacy-protocol-support/
Jun 03, 2022 11:28:45 AM org.openqa.selenium.W3CCapabilityKeysValidator validateCapability
WARNING: Support for Legacy Capabilities is deprecated; You are sending "automationName" which is an invalid capability. Please update to W3C Syntax: https://www.selenium.dev/blog/2022/legacy-protocol-support/
Jun 03, 2022 11:28:45 AM org.openqa.selenium.W3CCapabilityKeysValidator validateCapability
WARNING: Support for Legacy Capabilities is deprecated; You are sending "noReset" which is an invalid capability. Please update to W3C Syntax: https://www.selenium.dev/blog/2022/legacy-protocol-support/
Jun 03, 2022 11:28:45 AM org.openqa.selenium.W3CCapabilityKeysValidator validateCapability
WARNING: Support for Legacy Capabilities is deprecated; You are sending "unicodeKeyboard" which is an invalid capability. Please update to W3C Syntax: https://www.selenium.dev/blog/2022/legacy-protocol-support/
 ```
2. Appium is recommending to BaseOption class to create the driver.

#### If there is no driver-specific options class for your driver then either use BaseOptions builder as the base class to define your capabilities or request driver developers to add one. Do not use DesiredCapabilities class for this purpose in W3C context. 

#### Time 
All methods that use TimeUnit class or where the time is passed as a simple numeric value were replaced with their alternatives using java.time.Duration class.

#### MobileElement
DefaultGenericMobileElement class has been removed completely together with its descendants (MobileElement, IOSElement, AndroidElement etc.). Use WebElement instead.

#### Touch Actions
The TouchAction and MultiTouchAction classes have been deprecated. The support of these actions will be removed from future Appium versions. Please use W3C Actions instead or the corresponding extension methods for the driver (if available). Check

##### SCROLL – THE BEHAVIOR
* Set the event 
* Move finger into starting position 
  - Determine scroll area dimensions 
  - Determine the X&Y on screen 
* Finger comes in contact with the screen 
* Wait for a bit 
* Finger moves to position 
  - Determine X & Y on screen 
  - Speed 
* Finger moves up from the screen

```Java
//Scrollable Element
WebElement ele01 = driver.findElement(AppiumBy.id("elementID"));

int centerX = ele01.getRect().x + (ele01.getSize().width/2);

double startY = ele01.getRect().y + (ele01.getSize().height * 0.9);

double endY = ele01.getRect().y + (ele01.getSize().height * 0.1);
//Type of Pointer Input
PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
//Creating Sequence object to add actions
Sequence swipe = new Sequence(finger,1);
//Move finger into starting position
swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
//Finger comes down into contact with screen
swipe.addAction(finger.createPointerDown(0));
//Finger moves to end position
swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
        PointerInput.Origin.viewport(),centerX, (int)endY));
//Get up Finger from Srceen
swipe.addAction(finger.createPointerUp(0));

//Perform the actions
driver.perform(Arrays.asList(swipe));
```

To be continued.....                                                                                                       
