package com.example.automation_db.tests;

import com.example.automation_db.base.BaseTest;
import com.example.automation_db.pages.GooglePage;
import com.example.automation_db.utils.ResultSender;
import com.example.automation_db.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void googleSearchTest() {
        long startTime = System.currentTimeMillis();

        try {
            driver.get("https://www.google.com");

            GooglePage googlePage = new GooglePage(driver);
            googlePage.search("Selenium Automation");

            Thread.sleep(2000);

            String title = driver.getTitle();

            // Natural validation
            Assert.assertTrue(
                    title.toLowerCase().contains("selenium"),
                    "Search result title does not contain 'Selenium'"
            );

            long executionTime = System.currentTimeMillis() - startTime;
            ResultSender.sendResult("GoogleSearchTest", "PASS", executionTime);

        } catch (Throwable e) {
            long executionTime = System.currentTimeMillis() - startTime;

            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "GoogleSearchTest");
            ResultSender.sendResult("GoogleSearchTest", "FAIL", executionTime, screenshotPath);

            throw new RuntimeException(e);
        }
    }
}