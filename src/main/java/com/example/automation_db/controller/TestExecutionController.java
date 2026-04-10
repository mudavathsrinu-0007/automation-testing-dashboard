package com.example.automation_db.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class TestExecutionController {

    @PostMapping("/api/run-tests")
    public String runTests() {
        try {
            // Start a new run before executing tests
            URL url = new URL("http://localhost:8080/api/start-run");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.getResponseCode();
            conn.disconnect();

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", ".\\mvnw.cmd clean test"
            );

            processBuilder.directory(new File(System.getProperty("user.dir")));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                return "Tests executed successfully";
            } else {
                return "Test execution failed:\n" + output;
            }

        } catch (Exception e) {
            return "Failed to execute tests: " + e.getMessage();
        }
    }
}