package parallel;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"parallel", "StepDefinitions"})


public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeTest(@Optional("chrome") String browser) {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driverThreadLocal.set(new ChromeDriver());
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            driverThreadLocal.set(new EdgeDriver());
        } else {
            throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }
        WebDriver driver = driverThreadLocal.get();
        System.out.println("Launching " + browser + " browser...");
        driver.get("https://www.topky.sk/");
        System.out.println("Opened URL: " + driver.getCurrentUrl());
    }

    @AfterMethod
    public void afterScenario() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}





