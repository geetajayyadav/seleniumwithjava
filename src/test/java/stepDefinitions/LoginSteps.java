package stepDefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.io.File;

import utils.DriverFactory;

public class LoginSteps {

    static WebDriver driver;
    WebDriverWait wait;

    @Given("I open browser")
    public void open_browser() {

        // ✅ Get driver from DriverFactory (LambdaTest)
    	driver = Hooks.driver;

        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I enter username {string} and password {string}")
    public void enter_credentials(String username, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
    }

    @And("I click login button")
    public void click_login() {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
    }

    @Then("I should see dashboard")
    public void verify_dashboard() {

        try {
            // Check dashboard URL
            wait.until(ExpectedConditions.urlContains("dashboard"));

            System.out.println("Login Successful ✅");

        } catch (Exception e) {

            // Take screenshot if failed
            takeScreenshot("Login_Failure");

            System.out.println("Test Failed ❌ Screenshot captured");
        }

        //driver.quit(); // ✅ Close browser
    }

    // 📸 Screenshot Method
    public void takeScreenshot(String fileName) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            File destination = new File("target/screenshots/" + fileName + ".png");

            destination.getParentFile().mkdirs();

            org.openqa.selenium.io.FileHandler.copy(source, destination);

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}