package tutorials;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Tutorials07 {
    public static void main(String[] args) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("710KPGS0516231").setAppPackage("net.skyscanner.android.main")
                .setAppActivity("net.skyscanner.app.presentation.view.splash.SplashActivity")
                .setUnlockKey("6263")
                .setNoReset(true)
                .eventTimings();

        AppiumDriver driver = null;
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), options);
            Thread.sleep(3000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement ele01 = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Flights' or @resource-id='net.skyscanner.android.main:id/flights_icon']"));

        //fullscreenScreenshot(driver);

        elementScreenshot(driver,ele01);
    }

    public static String elementScreenshot(AppiumDriver driver, WebElement ele)
    {

        File screenshotLocation = null;
        try{
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            BufferedImage fullImg = ImageIO.read(scrFile);
//Get the location of element on the page
            Point point = ele.getLocation();
//Get width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();
//Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,
                    eleHeight);
            ImageIO.write(eleScreenshot, "png", scrFile);

            String path = "screenshots/" + UUID.randomUUID() + "" + ".png";

            screenshotLocation = new File(System.getProperty("user.dir") + "/" + path);
            FileUtils.copyFile(scrFile, screenshotLocation);

            System.out.println(screenshotLocation.toString());

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return screenshotLocation.toString();

    }

    public static String fullscreenScreenshot(AppiumDriver driver) {

        File screenshotLocation = null;
        try{
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

          /*  BufferedImage fullImg = ImageIO.read(scrFile);

            ImageIO.write(fullImg, "png", scrFile);*/

            String path = "screenshots/" + UUID.randomUUID()  + ".png";

            screenshotLocation = new File(System.getProperty("user.dir") + "/" + path);
            FileUtils.copyFile(scrFile, screenshotLocation);

            System.out.println(screenshotLocation.toString());

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return screenshotLocation.toString();

    }
}
