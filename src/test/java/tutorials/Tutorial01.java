package tutorials;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Tutorial01 {

    AppiumDriver driver;

    @BeforeTest
    public void setup(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
       /* JSON Wire Protocol */
       /* capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "710KPGS0516231");
        capabilities.setCapability("appPackage", "net.skyscanner.android.main");
        capabilities.setCapability("appActivity", "net.skyscanner.app.presentation.view.splash.SplashActivity");
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("unicodeKeyboard", "true");*/
        /* W3C Protocol */
        /*capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appium:deviceName", "710KPGS0516231");
        capabilities.setCapability("appium:appPackage", "net.skyscanner.android.main");
        capabilities.setCapability("appium:appActivity", "net.skyscanner.app.presentation.view.splash.SplashActivity");
        capabilities.setCapability("appium:automationName", "UIAutomator2");
        capabilities.setCapability("appium:noReset", true);
        capabilities.setCapability("appium:unicodeKeyboard", "true");*/

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
        //driver.findElement(AppiumBy.accessibilityId("Flights")).click();

        horizontalScroll(driver);



    }

    public void verticalScroll(AppiumDriver driver){
        //Creating Vertical Scroll Event
        //Scrollable Element
        WebElement ele01 = driver.findElement(AppiumBy.id("net.skyscanner.android.main:id/content"));

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

    }

    public void horizontalScroll(AppiumDriver driver){
        //Creating Horizontal Scroll Event
        //Scrollable Element
        WebElement ele01 = driver.findElement(AppiumBy.id("net.skyscanner.android.main:id/nav_card_recycle_view"));

        int centerY = ele01.getRect().y + (ele01.getSize().height/2);

        double startX = ele01.getRect().x + (ele01.getSize().width * 0.9);

        double endX = ele01.getRect().x + (ele01.getSize().width * 0.1);
        //Type of Pointer Input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        //Creating Sequence object to add actions
        Sequence swipe = new Sequence(finger,1);
        //Move finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),(int)startX,centerY));
        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),(int)endX,centerY));
        //Get up Finger from Srceen
        swipe.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(Arrays.asList(swipe));

        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @AfterTest
    public void tearDown(){
        //driver.quit();
    }
}
