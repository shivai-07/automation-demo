package com.shashank.example.automationdemo.pages;

//POM --> Page Object Model

import com.aventstack.extentreports.ExtentTest;
import com.shashank.example.automationdemo.uistore.LoginPageLocators;
import com.shashank.example.automationdemo.utill.ExtentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPageActions
{
    private WebDriver driver;
    private static Logger logger = LogManager.getLogger(LoginPageActions.class);

    public LoginPageActions(WebDriver driver)
    {
        this.driver = driver;
    }
    public void enterUsername(String userName)
    {
        driver.findElement(LoginPageLocators.usernameLocator).sendKeys(userName);
    }
    public void enterPassword(String pass)
    {
        driver.findElement(LoginPageLocators.passwordLocator).sendKeys(pass);
    }

    public  void clickLoginButton()
    {
        driver.findElement(LoginPageLocators.loginButtonLocator).click();
    }

    public void verifyInvalidTextContains(String expectedText, ExtentTest test)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.errorMessage));

        String errorMsg = driver.findElement(LoginPageLocators.errorMessage).getText();
        try {
            Assert.assertTrue(errorMsg.contains("Invalid credentials"));
            test.pass("Invalid Login displayed error message correctly");
            ExtentManager.attachScreenshotToReport(test,
                    "Invalid_Login",
                    "Invalid Login Pass");
        } catch (AssertionError e) {
            test.fail("Invalid Login did not show error");
            ExtentManager.attachScreenshotToReport(test,
                    "Invalid_Login",
                    "Invalid Login Failed");
            logger.error("Assertion Failed for Invalid Logging. an Error: "+e.getMessage());
            throw e;
        }

    }
}
