package com.shashank.example.automationdemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.shashank.example.automationdemo.uistore.DashboradPageLocators;
import com.shashank.example.automationdemo.utill.ExtentManager;
import com.shashank.example.automationdemo.utill.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboradPageActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extentTest;
    private static Logger logger = LoggerManager.getLogger(DashboradPageActions.class);

    public DashboradPageActions(WebDriver driver,ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyDashboardText(String expectedText) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(DashboradPageLocators.dashboardHeader));
        extentTest.info("Dashboard page loaded successfully");

        String dashboardText = driver.findElement(DashboradPageLocators.dashboardHeader).getText();
        try {
            Assert.assertEquals(dashboardText, expectedText);
            logger.info("Dashboard page loaded successfully");
            extentTest.pass("Valid Login Successful.........");
            ExtentManager.attachScreenshotToReport(extentTest,
                    "Valid_Login",
                    "Valid Login Pass");
        } catch (AssertionError e) {
            extentTest.fail("Valid Login Failed");
            ExtentManager.attachScreenshotToReport(extentTest,
                    "Valid_Login",
                    "Valid Login Failed");
            logger.error("Assertion Failed for Valid Login Page. an Error: ".formatted(e.getMessage()));
        }
    }
}
