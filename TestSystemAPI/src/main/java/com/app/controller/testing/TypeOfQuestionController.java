package com.app.controller.testing;

import com.app.model.TypeOfQuestion;
import com.app.service.testing.TypeOfQuestionService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import exceptions.QuestionTypeAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questionTypes")
public class TypeOfQuestionController {

    private TypeOfQuestionService typeOfQuestionService;

    public TypeOfQuestionController(TypeOfQuestionService typeOfQuestionService) {
        this.typeOfQuestionService = typeOfQuestionService;
    }

    @PostMapping
    public ResponseEntity<?> createNewQuestionType(@Parameter(description = "Name of the question type") @RequestParam String name) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(typeOfQuestionService.addTypeOfQuestion(name));

        }catch(QuestionTypeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PatchMapping
    public ResponseEntity<?> setQuestionType(@Parameter(description = "id of the question type") @RequestParam Long id,
                                             @Parameter(description = "Name of the question type") @RequestParam String name) {
        try {
            return ResponseEntity.ok().body(typeOfQuestionService.setTypeOfQuestion(id, name));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteQuestionType(@Parameter(description = "id of the question type") @RequestParam Long id) {
        try {
            typeOfQuestionService.deleteTypeOfQuestion(id);
            return ResponseEntity.ok("question type was deleted");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TypeOfQuestion> getAllTypesOfQuestion() {
        return typeOfQuestionService.getAllNamesOfType();
    }
}
