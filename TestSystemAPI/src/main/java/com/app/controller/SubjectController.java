package com.app.controller;

import com.app.model.Subject;
import com.app.service.SubjectService;
import exceptions.SubjectAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping()
    public ResponseEntity<?> createNewSubject(@Parameter(description = "Name of the subject") @RequestParam String name,
                                              @Parameter(description = "Teacher id of the subject") @RequestParam Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createNewSubject(name, userId));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (SubjectAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PatchMapping
    public ResponseEntity<?> setSubject(@Parameter(description = "id of the subject") @RequestParam Long id,
                                             @Parameter(description = "Name of the subject") @RequestParam String name,
                                             @Parameter(description = "Teacher of the subject") @RequestParam Long userId) {
        try {
            return ResponseEntity.ok().body(subjectService.setSubject(id, name, userId));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubject(@Parameter(description = "id of the subject") @RequestParam Long id) {
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok("subject deleted");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> fetchSubjects(@Parameter(description = "id of the teacher") @RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(subjectService.fetchSubject(id));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
