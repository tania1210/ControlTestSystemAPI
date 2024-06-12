package com.app.controller.testing;

import com.app.model.Question;
import com.app.service.testing.QuestionService;
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
                                               @Parameter(description = "Type of the question") @RequestParam String type,
                                               @Parameter(description = "Test id of the question") @RequestParam Long testId) {
        try {
            Long id = questionService.createNewQuestion(text, type, testId);
            return ResponseEntity.ok(id);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datas was incorrect");
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("test not found");
        }
    }

    @PatchMapping
    public ResponseEntity<String> setQuestion(@Parameter(description = "id of the question") @RequestParam Long id,
                                              @Parameter(description = "text of the question") @RequestParam String text,
                                              @Parameter(description = "type of the question") @RequestParam String type,
                                              @Parameter(description = "test of the question") @RequestParam Long testId) {
        try {
            questionService.setQuestionDatas(id, text, type, testId);
            return ResponseEntity.ok("Question datas was update");
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found this type of question");
        }catch(NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any data was change. Question wasn't found");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteQuestion(@Parameter(description = "id of the question") @RequestParam Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.ok("Question was delete");
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question wasn`t found. Wrong id");
        }
    }

    @GetMapping("RETURN ONLY IDS")
    public List<Question> fetchQuestions(@Parameter(description = "id of the test") @RequestParam Long id) {
        return questionService.fetchQuestions(id);
    }
}
