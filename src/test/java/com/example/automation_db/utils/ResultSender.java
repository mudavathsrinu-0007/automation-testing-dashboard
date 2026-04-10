package com.example.automation_db.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResultSender {

    // Existing method for PASS / simple save
    public static void sendResult(String testName, String status, long executionTime) {
        try {
            URL url = new URL("http://localhost:8080/api/test-results");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = String.format(
                    "{\"testName\":\"%s\",\"status\":\"%s\",\"executionTime\":\"%d\"}",
                    testName, status, executionTime
            );

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 200 || responseCode == 201) {
                System.out.println("Result sent successfully for: " + testName);
            } else {
                System.out.println("Failed to send result for: " + testName +
                        " | HTTP Response Code: " + responseCode);
            }

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Could not connect to backend API. Result not stored for: " + testName);
            e.printStackTrace();
        }
    }

    // New method for FAIL with screenshot path
    public static void sendResult(String testName, String status, long executionTime, String screenshotPath) {
        try {
            URL url = new URL("http://localhost:8080/api/test-results");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String safeScreenshotPath = screenshotPath == null ? "" : screenshotPath.replace("\\", "\\\\");

            String json = String.format(
                    "{\"testName\":\"%s\",\"status\":\"%s\",\"executionTime\":\"%d\",\"screenshotPath\":\"%s\"}",
                    testName, status, executionTime, safeScreenshotPath
            );

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 200 || responseCode == 201) {
                System.out.println("Result with screenshot sent successfully for: " + testName);
            } else {
                System.out.println("Failed to send result with screenshot for: " + testName +
                        " | HTTP Response Code: " + responseCode);
            }

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Could not connect to backend API. Result with screenshot not stored for: " + testName);
            e.printStackTrace();
        }
    }
}