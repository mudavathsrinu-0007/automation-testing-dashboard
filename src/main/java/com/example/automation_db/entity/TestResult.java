package com.example.automation_db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;
    private String status;
    private String executionTime;
    private Long runId;

    // 🔥 NEW FIELD (safe addition)
    private String screenshotPath;

    public TestResult() {
    }

    public TestResult(String testName, String status, String executionTime, Long runId) {
        this.testName = testName;
        this.status = status;
        this.executionTime = executionTime;
        this.runId = runId;
    }

    public Long getId() {
        return id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    // 🔥 NEW GETTER
    public String getScreenshotPath() {
        return screenshotPath;
    }

    // 🔥 NEW SETTER
    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }
}