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
    private static Logger logger = LoggerManager.getLogger(DashboradPageActions.class);

    public DashboradPageActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyDashboardText(String expectedText, ExtentTest test) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(DashboradPageLocators.dashboardHeader));
        test.info("Dashboard page loaded successfully");

        String dashboardText = driver.findElement(DashboradPageLocators.dashboardHeader).getText();
        try {
            Assert.assertEquals(dashboardText, expectedText);
            test.pass("Valid Login Successful.........");
            ExtentManager.attachScreenshotToReport(test,
                    "Valid_Login",
                    "Valid Login Pass");
        } catch (AssertionError e) {
            test.fail("Valid Login Failed");
            ExtentManager.attachScreenshotToReport(test,
                    "Valid_Login",
                    "Valid Login Failed");
            logger.error("Assertion Failed for Invalid Login Page. an Error: "+e.getMessage());
            throw e;
        }
    }
}
