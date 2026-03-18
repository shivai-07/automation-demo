package com.shashank.example.automationdemo.utill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * BaseTest class provides common setup and teardown methods for all TestNG test classes.
 * <p>
 * This class initializes the WebDriver, opens the browser before each test,
 * and closes it after each test to ensure test isolation and stability.
 * </p>
 *
 * @author YourName
 * @version 1.0
 * @since 2026-03-17
 */
public class BaseTest {

    protected static WebDriver driver;

    /**
     * Initializes Chrome WebDriver with default options and navigates to the login page.
     * <p>
     * This method runs before every test method to ensure a fresh browser session.
     * </p>
     *
     * @throws WebDriverException if the browser fails to start
     */
    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    /**
     * Closes the browser and quits the WebDriver session.
     * <p>
     * This method runs after every test method to release resources
     * and ensure no leftover browser instances affect subsequent tests.
     * </p>
     *
     * @throws WebDriverException if the browser fails to close
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
