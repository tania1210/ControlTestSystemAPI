package com.app.controller.session;

import com.app.service.session.StudentAnswerService;
import exceptions.StudentAnswerAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class StudentAnswerController {

    private StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

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
