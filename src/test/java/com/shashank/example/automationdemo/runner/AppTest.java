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
        logger.info("Start validLogin Testing");
        try{
            extentTest = extentReports.createTest("TC1-Valid Login");

            LoginPageActions loginPageActions = new LoginPageActions(driver);

            String username = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",1,0);
            loginPageActions.enterUsername(username);
            logger.info("Username read from Excel File: " + username);
            extentTest.info("Username entered as " + username);

            String password = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",1,1);
            loginPageActions.enterPassword(password);
            logger.info("Password read from Excel File: " + password);
            extentTest.info("Password entered  as " + password);

            loginPageActions.clickLoginButton();
            logger.info("Login Button clicked");
            extentTest.info("Clicked on login button");

            DashboradPageActions dashboradPageActions = new DashboradPageActions(driver);
            String expectedText = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",1,2);
            logger.info("Expected text read from Excel File: " + expectedText);
            dashboradPageActions.verifyDashboardText(expectedText,extentTest);
            logger.info("Verify Dashboard Text Completed");
        }catch(Exception e){
            logger.error("Exception occured while validating Login Page. an Error: "+e.getMessage());
        }
        logger.info("End validLogin Testing");
    }

    @Test(enabled = false)
    public void inValidLogin() {
        logger.info("Start invalid Login Testing");
        try
        {
            extentTest = extentReports.createTest("TC2-Invalid Login");
            logger.info("TC2-Invalid Login test added to report.");

            LoginPageActions loginPageActions = new LoginPageActions(driver);

            String username = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",2,0);
            loginPageActions.enterUsername(username);
            logger.info("Username read from Excel File: " + username);
            extentTest.info("Username entered as " + username);

            String password = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",2,1);
            loginPageActions.enterPassword(password);
            logger.info("Password read from Excel File: " + password);
            extentTest.info("Password entered  as " + password);

            loginPageActions.clickLoginButton();
            logger.info("Login Button clicked");
            extentTest.info("Clicked on login button");

            String expectedText = ExcelManager.readData("./testdata/logindata.xlsx","Sheet1",2,2);
            logger.info("Expected text read from Excel File: " + expectedText);
            loginPageActions.verifyInvalidTextContains(expectedText,extentTest);
        }catch(Exception e){
            logger.error("Exception occured while validating Login Page. an Error: "+e.getMessage());
        }
        logger.info("Verify Invalid Text Contains Completed");
    }
}
