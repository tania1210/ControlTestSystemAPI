package com.app.controller.testing;

import com.app.model.Question;
import com.app.service.testing.QuestionService;
import exceptions.QuestionAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> createNewQuestion(@Parameter(description = "text of the question") @RequestParam String text,
                                               @Parameter(description = "Type id of the question") @RequestParam Long typeId,
                                               @Parameter(description = "Test id of the question") @RequestParam Long testId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createNewQuestion(text, typeId, testId));
        }catch (QuestionAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> setQuestion(@Parameter(description = "id of the question") @RequestParam Long id,
                                              @Parameter(description = "text of the question") @RequestParam String text,
                                              @Parameter(description = "type of the question") @RequestParam Long typeId,
                                              @Parameter(description = "test of the question") @RequestParam Long testId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionService.setQuestionDatas(id, text, typeId, testId));
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteQuestion(@Parameter(description = "id of the question") @RequestParam Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.ok("Question's been deleted");
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> fetchQuestions(@Parameter(description = "id of the test") @RequestParam Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionService.fetchQuestions(id));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
