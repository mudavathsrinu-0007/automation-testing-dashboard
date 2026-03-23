package com.example.automation_db.tests;

import com.example.automation_db.base.BaseTest;
import com.example.automation_db.pages.LoginPage;
import com.example.automation_db.utils.ResultSender;
import com.example.automation_db.utils.ScreenshotUtil;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {

        long startTime = System.currentTimeMillis();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage("https://the-internet.herokuapp.com/login");
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String message = loginPage.getMessage();

        String status;

        if (message.contains("You logged into a secure area!")) {
            System.out.println("TEST PASSED");
            status = "PASS";
        } else {
            System.out.println("TEST FAILED");
            status = "FAIL";
            ScreenshotUtil.captureScreenshot(driver, "LoginTest_Failed");
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        ResultSender.sendResult("LoginTest", status, executionTime);
    }
}