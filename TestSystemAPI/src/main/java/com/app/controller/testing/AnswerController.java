package com.app.controller.testing;

import com.app.model.Answer;
import com.app.service.testing.AnswerService;
import exceptions.AnswerAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answers")
public class AnswerController {

    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(summary = "створити відповідь", description = "повертає збережену відповідь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "відповідь успішно створено"),
            @ApiResponse(responseCode = "404", description = "питання не знайдено")
    })
    @PostMapping
    public ResponseEntity<?> addAnswerSession(@Parameter(description = "text of the answer") @RequestParam String text,
                                              @Parameter(description = "status of the answer") @RequestParam boolean isCorrect,
                                              @Parameter(description = "id of the question") @RequestParam Long questionId) {
        try {
            return ResponseEntity.ok().body(answerService.createNewAnswer(text, isCorrect, questionId));
        }catch (AnswerAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "оновити дані відповіді", description = "змінює дані відповіді")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "дані оновлено"),
            @ApiResponse(responseCode = "404", description = "питання не знайдено")
    })
    @PatchMapping
    public ResponseEntity<?> setAnswer(@Parameter(description = "id of the answer") @RequestParam Long id,
                                            @Parameter(description = "text of the answer") @RequestParam String answerText,
                                            @Parameter(description = "status of the answer") @RequestParam boolean isCorrect,
                                            @Parameter(description = "question id of the answer") @RequestParam Long questionId) {
        try {
            return ResponseEntity.ok().body(answerService.setAnswerDatas(id, answerText, isCorrect, questionId));
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "видалити відповідь", description = "видаляє відповідь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "відповідь успішно видалено"),
            @ApiResponse(responseCode = "404", description = "відповідь не знайдено")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteAnswer(@Parameter(description = "id of the answer") @RequestParam Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Answer deleted");
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "отримати список відповідей для питання", description = "повертає список відповідей для конкретного питання")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "відповіді отримано"),
            @ApiResponse(responseCode = "404", description = "питання не знайдено")
    })
    @GetMapping
    public ResponseEntity<?> fetchAnswers(@Parameter(description = "id of the question") @RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(answerService.fetchAnswers(id));
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
