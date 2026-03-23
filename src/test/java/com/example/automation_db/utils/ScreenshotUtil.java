package com.example.automation_db.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path screenshotsDir = Path.of("screenshots");
            Files.createDirectories(screenshotsDir);

            Path destPath = screenshotsDir.resolve(testName + ".png");

            Files.copy(srcFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: " + destPath.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}