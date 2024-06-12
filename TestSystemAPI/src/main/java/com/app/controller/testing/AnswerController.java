package com.app.controller.testing;

import com.app.model.Answer;
import com.app.service.testing.AnswerService;
import io.swagger.v3.oas.annotations.Parameter;
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

    @PostMapping
    public ResponseEntity<?> addAnswerSession(@Parameter(description = "text of the answer") @RequestParam String text,
                                              @Parameter(description = "status of the answer") @RequestParam boolean isCorrect,
                                              @Parameter(description = "id of the question") @RequestParam Long questionId) {
        try {
            Long id = answerService.createNewAnswer(text, isCorrect, questionId);
            return ResponseEntity.ok(id);
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("question not found");
        }
    }

    @PatchMapping
    public ResponseEntity<String> setAnswer(@Parameter(description = "id of the answer") @RequestParam Long id,
                                            @Parameter(description = "text of the answer") @RequestParam String answerText,
                                            @Parameter(description = "status of the answer") @RequestParam boolean isCorrect) {
        try {
            answerService.setAnswerDatas(id, answerText, isCorrect);
            return ResponseEntity.ok("Answer datas was update");
        }catch(NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any data was change. Answer wasn't found");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAnswer(@Parameter(description = "id of the answer") @RequestParam Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Answer was delete");
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Answer wasn`t found. Wrong id");
        }
    }

    @GetMapping
    public List<Answer> fetchAnswers(@Parameter(description = "id of the question") @RequestParam Long id) {
        return answerService.fetchAnswers(id);
    }
}
