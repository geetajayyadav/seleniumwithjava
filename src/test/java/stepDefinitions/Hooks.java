package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.WebDriver;

import utils.DriverFactory;

import utils.XrayUtils;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {

        // Start browser from LambdaTest
        driver = DriverFactory.initDriver();
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        XrayUtils.uploadResults(); // ✅ REQUIRED
    }
}