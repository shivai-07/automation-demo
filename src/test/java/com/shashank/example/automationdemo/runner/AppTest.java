package com.shashank.example.automationdemo.runner;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.shashank.example.automationdemo.pages.DashboradPageActions;
import com.shashank.example.automationdemo.pages.LoginPageActions;
import com.shashank.example.automationdemo.utill.BaseTest;
import com.shashank.example.automationdemo.utill.ExcelManager;
import com.shashank.example.automationdemo.utill.ExtentManager;

import com.shashank.example.automationdemo.utill.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AppTest extends BaseTest {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static Logger logger = LoggerManager.getLogger(AppTest.class);

    @BeforeSuite
    public void setUpReport() {
        logger.info("Start set up report");
        extentReports = ExtentManager.createReport("OHRM Automation Report");
    }

    @AfterSuite
    public void tearDownReport() {
        logger.info("End set up report");
        ExtentManager.flushReports();
    }


    @Test
    public void validLogin() {
        System.out.println(logger);
        logger.info("Start valid Login Testing");
        try{
            extentTest = extentReports.createTest("TC1-Valid Login");

            LoginPageActions loginPageActions = new LoginPageActions(driver,extentTest);

            String userName = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",1,0);
            logger.info("Username read from Excel File: %s".formatted(userName));
            loginPageActions.enterUsername(userName);

            String pass = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",1,1);
            logger.info("Password read from Excel File: %s".formatted(pass));
            loginPageActions.enterPassword(pass);

            loginPageActions.clickLoginButton();

            DashboradPageActions dashboradPageActions = new DashboradPageActions(driver,extentTest);
            String expectedText = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",1,2);
            logger.info("Expected text read from Excel File: %s".formatted(expectedText));
            dashboradPageActions.verifyDashboardText(expectedText);
        }catch(Exception e){
            logger.error("Exception occured while validating Login Page. an Error: %s".formatted(e.getMessage()));
        }
        logger.info("End valid Login Testing");
    }

    @Test(enabled = true)
    public void inValidLogin() {
        logger.info("Start invalid Login Testing");
        try
        {
            extentTest = extentReports.createTest("TC2-Invalid Login");
            logger.info("TC2-Invalid Login test added to report.");

            LoginPageActions loginPageActions = new LoginPageActions(driver,extentTest);

            String username = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",2,0);
            logger.info(String.format("Username read from Excel File: %s", username));
            loginPageActions.enterUsername(username);

            String password = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",2,1);
            logger.info(String.format("Password read from Excel File: %s", password));
            loginPageActions.enterPassword(password);

            loginPageActions.clickLoginButton();

            String expectedText = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",2,2);
            logger.info("Expected text read from Excel File: %s".formatted(expectedText));
            loginPageActions.verifyInvalidTextContains(expectedText);
        }catch(Exception e){
            logger.error("Exception occured while validating Login Page. an Error: %s".formatted(e.getMessage()));
        }
        logger.info("End invalid Login Testing");
    }
}
