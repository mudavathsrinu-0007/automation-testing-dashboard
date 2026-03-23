package com.example.automation_db.controller;

import com.example.automation_db.entity.TestResult;
import com.example.automation_db.repository.TestResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-results")
public class TestResultController {

    private final TestResultRepository repository;

    public TestResultController(TestResultRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public TestResult saveResult(@RequestBody TestResult result) {
        return repository.save(result);
    }

    @GetMapping
    public List<TestResult> getResults() {
        return repository.findAll();
    }
}