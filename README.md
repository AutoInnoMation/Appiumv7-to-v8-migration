# Appium v7-to-v8-migration Guide
  
  **Appium v7-to-v8 migration Guide**

Much waited Selenium 4 has been released on September,2021 with a bunch of new features along with they removes support for the legacy protocol and uses the W3C WebDriver standard by default under the hood. Since that appium has been working to get seamless experience of Selenium 4. Beta version of Appium clients were updated by urgrading the selenium dependency but there was lots of glitches. Now they have launched the stable version with fixes, but still there are some issue present. Hoping they will fix those issues soon. Let talk's about the changes happened due to this upgradation. 

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
1. "Appium:" tag have to use with capability name to tell server specifically that these are appium capabilities. Otherwise users will get error messages like :
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

To be continued.....                                                                                                       
