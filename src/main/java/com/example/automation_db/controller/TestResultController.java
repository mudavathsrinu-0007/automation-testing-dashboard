package com.example.automation_db.controller;

import com.example.automation_db.entity.TestResult;
import com.example.automation_db.repository.TestResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TestResultController {

    private final TestResultRepository testResultRepository;

    private static Long currentRunId = 1L;

    public TestResultController(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    // all saved results
    @GetMapping("/api/test-results")
    public List<TestResult> getAllResults() {
        return testResultRepository.findAll();
    }

    // save each test result with current runId
    @PostMapping("/api/test-results")
    public TestResult saveResult(@RequestBody TestResult testResult) {
        testResult.setRunId(currentRunId);
        return testResultRepository.save(testResult);
    }

    // start a new run before executing tests
    @PostMapping("/api/start-run")
    public String startNewRun() {
        TestResult latest = testResultRepository.findTopByOrderByRunIdDesc();

        if (latest == null || latest.getRunId() == null) {
            currentRunId = 1L;
        } else {
            currentRunId = latest.getRunId() + 1;
        }

        return "Started runId: " + currentRunId;
    }

    // latest run only
    @GetMapping("/api/latest-results")
    public List<TestResult> getLatestResults() {
        TestResult latest = testResultRepository.findTopByOrderByRunIdDesc();

        if (latest == null || latest.getRunId() == null) {
            return List.of();
        }

        return testResultRepository.findByRunId(latest.getRunId());
    }

    // all history
    @GetMapping("/api/history-results")
    public List<TestResult> getHistoryResults() {
        return testResultRepository.findAllByOrderByIdDesc();
    }
}