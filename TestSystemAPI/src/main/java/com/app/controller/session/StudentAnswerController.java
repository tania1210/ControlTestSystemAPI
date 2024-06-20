package com.app.controller.session;

import com.app.service.session.StudentAnswerService;
import exceptions.StudentAnswerAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sessionAnswers")
public class StudentAnswerController {

    private StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @Operation(summary = "додати обрану відповідь", description = "зберігає відповідь що обрав студент")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "обрану відповідь успішно збережено"),
            @ApiResponse(responseCode = "404", description = "не знайдено один із вказаних обʼєктів")
    })

    @PostMapping
    public ResponseEntity<?> addResponse(Long testId, Long questionId, Long answerId, Long studentId) {
        try {
            studentAnswerService.addResponse(testId, questionId, answerId, studentId);
            return ResponseEntity.status(HttpStatus.CREATED).body("answer was saving");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (StudentAnswerAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "змінити відповідь", description = "змінює відповідь на питання")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "відповідь змінено"),
            @ApiResponse(responseCode = "404", description = "відповідь до вказаного питання не знайдено. Користувач ще не відповів на це питання")
    })
    @PatchMapping
    public ResponseEntity<?> setResponse(Long testId, Long questionId, Long answerId, Long studentId) {
        try {
            studentAnswerService.setResponse(testId, questionId, answerId, studentId);
            return ResponseEntity.ok("answer was set");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
