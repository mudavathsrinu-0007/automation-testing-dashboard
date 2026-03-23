package com.example.automation_db.tests;

import com.example.automation_db.base.BaseTest;
import com.example.automation_db.pages.GooglePage;
import com.example.automation_db.utils.ResultSender;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void googleSearchTest() throws InterruptedException {

        long startTime = System.currentTimeMillis();

        driver.get("https://www.google.com");

        GooglePage google = new GooglePage(driver);

        google.search("Selenium Automation");

        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();

        String status;

        if(currentUrl.contains("Selenium+Automation")) {
            System.out.println("TEST PASSED");
            status = "PASS";
        } else {
            System.out.println("TEST FAILED");
            status = "FAIL";
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        ResultSender.sendResult("GoogleSearchTest", status, executionTime);
    }
}