package com.app.controller.session;

import com.app.service.session.TestActivationService;
import exceptions.TestDeactivatedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/activationTests")
public class TestActivationController {

    private TestActivationService testActivationService;

    public TestActivationController(TestActivationService testActivationService) {
        this.testActivationService = testActivationService;
    }

    @Operation(summary = "активувати тест", description = "змінює статус тесту на активний")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "тест успішно активовано"),
            @ApiResponse(responseCode = "404", description = "тест не знайдено")
    })
    @PostMapping
    public ResponseEntity<?> activateTest(@RequestParam LocalDateTime endTime, @RequestParam Long testId) {
        try {
            testActivationService.activateTest(endTime, testId);
            return ResponseEntity.ok("test activated");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "деактивувати тест", description = "змінює статус тесту на неактивний")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "тест було деактивовано"),
            @ApiResponse(responseCode = "404", description = "тест не знайдено")
    })
    @DeleteMapping
    public ResponseEntity<?> deactivateTest(@RequestParam Long testId) {
        try {
            testActivationService.deactivateTest(testId);
            return ResponseEntity.ok("test deactivated");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (TestDeactivatedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
