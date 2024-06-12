package com.app.controller.testing;

import com.app.service.testing.TypeOfQuestionService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question types")
public class TypeOfQuestionController {

    private TypeOfQuestionService typeOfQuestionService;

    public TypeOfQuestionController(TypeOfQuestionService typeOfQuestionService) {
        this.typeOfQuestionService = typeOfQuestionService;
    }

    @PostMapping
    public ResponseEntity<?> createNewQuestionType(@Parameter(description = "Name of the question type") @RequestParam String name) {
//        try {
            typeOfQuestionService.addTypeOfQuestion(name);
            return ResponseEntity.ok("question type was added");

//        }catch(InvalidFormatException e) {
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Type of time is not correct");
//        }

    }

    @PatchMapping
    public ResponseEntity<?> setQuestionType(@Parameter(description = "id of the question type") @RequestParam Long id,
                                             @Parameter(description = "Name of the question type") @RequestParam String name) {
        typeOfQuestionService.setTypeOfQuestion(id, name);
        return ResponseEntity.ok("question type was set");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteQuestionType(@Parameter(description = "id of the question type") @RequestParam Long id) {
        typeOfQuestionService.deleteTypeOfQuestion(id);
        return ResponseEntity.ok("question type was deleted");
    }

    @GetMapping("/getAllTypes")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<String> getAllTypesOfQuestion() {
        return typeOfQuestionService.showAllNamesOfType();
    }
}
