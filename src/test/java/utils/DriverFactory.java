package utils;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver initDriver() {

        try {
            String username = "antimyadav0608";
            String accessKey = "LT_EwQEgfFlEJtKAEKSzoFJ46sUpQvEaYGO4z0S3hkoUCO2MO1";

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("browserName", "Chrome");
            caps.setCapability("browserVersion", "latest");

            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("platformName", "Windows 11");
            ltOptions.put("build", "Cucumber Build");
            ltOptions.put("name", "Test Run");

            caps.setCapability("LT:Options", ltOptions);

            String hub = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

            driver = new RemoteWebDriver(new URL(hub), caps);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }
}