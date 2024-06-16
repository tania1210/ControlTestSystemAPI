package com.app.controller.session;

import com.app.service.session.TestResultService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/testResults")
public class TestResultController {

    private TestResultService testResultService;

    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    @PostMapping
    public ResponseEntity<?> calculateResultTest(@Parameter(description = "Score") @RequestParam byte score,
                                       @Parameter(description = "Id of the test") @RequestParam Long testId,
                                       @Parameter(description = "Id of the student") @RequestParam Long studentId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(calculateResultTest(score, testId, studentId));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @PatchMapping
//    public ResponseEntity<String> setResult(@Parameter(description = "Score") @RequestParam byte score) {
//
//        return null;
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> deleteResult(@Parameter(description = "Id of the result") @RequestParam Long id) {
//
//        return null;
//    }
}
