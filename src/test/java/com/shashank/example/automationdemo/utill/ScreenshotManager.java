package com.shashank.example.automationdemo.utill;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ScreenshotManager extends  BaseTest
{
    private static TakesScreenshot ts;

    public static void captureScreenshot(String screenshotName)
    {
        // Convert driver to TakesScreenshot
         ts = (TakesScreenshot) driver;

        // Capture screenshot as File
        File source = ts.getScreenshotAs(OutputType.FILE);

        // timestamp configuration
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        // Define destination path
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName+"_"+timeStamp + ".png";

        try {
            Path path = Paths.get(destination);
            Files.createDirectories(path.getParent()); // create folder if not exists
            Files.copy(source.toPath(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String captureScreenshotBase64(String screenshotName) {

        // Capture Base64 screenshot
        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        // Convert Base64 to byte array to save as file
        byte[] decodedImg = Base64.getDecoder().decode(base64Screenshot);

        // timestamp configuration
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        // Define destination path
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName+"_"+timeStamp + ".png";

        try {
            Path path = Paths.get(destination);
            Files.createDirectories(path.getParent()); // create folder if not exists
            Files.write(path, decodedImg);
            System.out.println("Screenshot saved at: " + destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Screenshot;
    }


}
