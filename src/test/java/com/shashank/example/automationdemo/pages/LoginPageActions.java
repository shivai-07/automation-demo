package com.shashank.example.automationdemo.pages;

//POM --> Page Object Model

import com.aventstack.extentreports.ExtentTest;
import com.shashank.example.automationdemo.uistore.LoginPageLocators;
import com.shashank.example.automationdemo.utill.ExtentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPageActions {
    private WebDriver driver;
    private ExtentTest extentTest;
    private static Logger logger = LogManager.getLogger(LoginPageActions.class);

    public LoginPageActions(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
    }

    public void enterUsername(String userName) {
        driver.findElement(LoginPageLocators.usernameLocator).sendKeys(userName);
        logger.info("Username entered as %s".formatted(userName));
        extentTest.info("Username entered as %s".formatted(userName));
    }

    public void enterPassword(String pass) {
        driver.findElement(LoginPageLocators.passwordLocator).sendKeys(pass);
        logger.info("Password entered  as %s".formatted(pass));
        extentTest.info("Password entered  as %s".formatted(pass));
    }

    public void clickLoginButton() {
        driver.findElement(LoginPageLocators.loginButtonLocator).click();
        logger.info("Login Button clicked");
        extentTest.info("Clicked on login button");

    }

    public void verifyInvalidTextContains(String invalidText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.errorMessage));

        String errorMsg = driver.findElement(LoginPageLocators.errorMessage).getText();

            Assert.assertTrue(errorMsg.contains(invalidText));
            logger.info("Verified %s Text contain in Error message.".formatted(invalidText));
            extentTest.pass("Invalid Login displayed error message correctly");
            ExtentManager.attachScreenshotToReport(extentTest,
                    "Invalid_Login",
                    "Invalid Login Pass");
        } catch (AssertionError | TimeoutException e) {
            extentTest.fail("Invalid Login did not show error");
            ExtentManager.attachScreenshotToReport(extentTest,
                    "Invalid_Login",
                    "Invalid Login Failed");
            logger.error("Assertion Failed for Invalid Logging. an Error: %s".formatted(e.getMessage()));
        }

    }
}
