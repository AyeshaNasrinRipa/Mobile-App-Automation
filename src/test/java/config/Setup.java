package config;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Setup {
    public AndroidDriver driver;
    public static final String PACKAGE_ID="com.google.android.calculator";

    @BeforeTest
    public AndroidDriver setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("appium:os","16");
        caps.setCapability("appium:app", System.getProperty("user.dir") + "/src/test/resources/calculator.apk");
        caps.setCapability("appium:appPackage", PACKAGE_ID);
        caps.setCapability("appium:appActivity","com.android.calculator2.Calculator");
        caps.setCapability("appium:automationName","UIAutomator2");

        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    @AfterTest
    public void closeApp(){
        driver.quit();
    }
}
