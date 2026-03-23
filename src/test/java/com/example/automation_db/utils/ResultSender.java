package com.example.automation_db.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultSender {

    public static void sendResult(String testName, String status, long executionTime) {

        try {
            URL url = new URL("http://localhost:8080/api/test-results");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = String.format(
                    "{\"testName\":\"%s\",\"status\":\"%s\",\"executionTime\":%d}",
                    testName, status, executionTime
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 200 || responseCode == 201) {
                System.out.println("Result sent successfully for: " + testName);
            } else {
                System.out.println("Failed to send result for: " + testName +
                        ". HTTP Response Code: " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("Could not connect to backend API. Result not stored for: " + testName);
        }
    }
}