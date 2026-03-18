package com.shashank.example.automationdemo.utill;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    /**
     * Creates or returns an ExtentReports instance with the given report name.
     *
     * @param reportName The name of the report file (without .html)
     * @return ExtentReports instance
     */
    public static ExtentReports createReport(String reportName) {
        if (extent == null) {
            if (reportName == null || reportName.isEmpty()) {
                reportName = "ExtentReport";
            }
            if (!reportName.endsWith(".html")) {
                reportName += ".html";
            }

            // Create 'report' folder if it doesn't exist
            String reportDir = System.getProperty("user.dir") + File.separator + "report";
            File dir = new File(reportDir);
            if (!dir.exists()) {
                dir.mkdir();
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(reportDir + File.separator + reportName);
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }


    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void attachScreenshotToReport(ExtentTest test, String screenshotName, String message) {
        if (extent != null) {
            try {
                // Capture Base64 screenshot
                String base64Screenshot = ScreenshotManager.captureScreenshotBase64(screenshotName);

                // Attach screenshot to ExtentReport
                test.info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } catch (Exception e) {
                e.printStackTrace();
                test.warning("Failed to attach screenshot: " + screenshotName);
            }
        }
    }
}
