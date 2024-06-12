package com.app.controller;

import com.app.model.Subject;
import com.app.service.SubjectService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping()
    public ResponseEntity<?> createNewSubject(@Parameter(description = "Name of the subject") @RequestParam String name,
                                              @Parameter(description = "Teacher id of the subject") @RequestParam Long userId) {
        return null;
    }

    @PatchMapping
    public ResponseEntity<String> setSubject(@Parameter(description = "id of the subject") @RequestParam Long id,
                                             @Parameter(description = "Name of the subject") @RequestParam String name,
                                             @Parameter(description = "Teacher of the subject") @RequestParam Long userId) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubject(@Parameter(description = "id of the subject") @RequestParam Long id) {

        return null;
    }

    @GetMapping
    public List<Subject> fetchSubjects(@Parameter(description = "id of the teacher") @RequestParam Long id) {
        return subjectService.fetchSubject(id);
    }

}
