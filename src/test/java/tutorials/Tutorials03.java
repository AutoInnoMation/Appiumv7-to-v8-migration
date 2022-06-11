package tutorials;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Tutorials03 {
        AppiumDriver driver;


        @BeforeTest
        public void setup() {


                UiAutomator2Options options = new UiAutomator2Options()
                        .setPlatformName("Android")
                        .setDeviceName("710KPGS0516231").setAppPackage("net.skyscanner.android.main")
                        .setAppActivity("net.skyscanner.app.presentation.view.splash.SplashActivity")
                        .setUnlockKey("6263")
                        .setNoReset(true)
                        .eventTimings();

                try {
                        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub/"), options);
                } catch (MalformedURLException e) {
                        e.printStackTrace();
                }
                driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);
        }

        @Test
        public void doTest(){
                WebElement ele01 = driver.findElement(AppiumBy.id("net.skyscanner.android.main:id/content"));

               driver.execute("mobile:scroll");
        }
}

