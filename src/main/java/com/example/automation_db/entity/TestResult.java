package com.example.automation_db.entity;

import jakarta.persistence.*;

@Entity
@Table(name="test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;
    private String status;
    private String executionTime;

    public TestResult() {}

    public TestResult(String testName, String status, String executionTime) {
        this.testName = testName;
        this.status = status;
        this.executionTime = executionTime;
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
}