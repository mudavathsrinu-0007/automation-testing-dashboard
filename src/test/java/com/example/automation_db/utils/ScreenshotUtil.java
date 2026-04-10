package com.example.automation_db.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // 🔥 SAVE INSIDE STATIC FOLDER
            String folderPath = "src/main/resources/static/screenshots/";
            String fileName = testName + "_" + timestamp + ".png";

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(folderPath + fileName);

            Files.copy(srcFile.toPath(), destFile.toPath());

            // 🔥 RETURN ONLY URL PATH (IMPORTANT)
            return "screenshots/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}