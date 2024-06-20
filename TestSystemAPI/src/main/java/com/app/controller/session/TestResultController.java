package com.app.controller.session;

import com.app.service.session.TestResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "обчислити результат тестування", description = "обчислює бал за тест")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "записано новий результат тестування"),
            @ApiResponse(responseCode = "404", description = "сутність не знайдено")
    })
    @PostMapping
    public ResponseEntity<?> calculateResultTest(@Parameter(description = "Id of the test") @RequestParam Long testId,
                                       @Parameter(description = "Id of the student") @RequestParam Long studentId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(testResultService.calculateResultTest(testId, studentId));
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
