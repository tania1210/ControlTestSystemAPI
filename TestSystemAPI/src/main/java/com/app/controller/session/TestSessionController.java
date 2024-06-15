package com.app.controller.session;

import com.app.model.Test;
import com.app.service.session.TestSessionService;
import exceptions.StudentAnswerAlreadyExistsException;
import exceptions.TestDeactivatedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sessions")
public class TestSessionController {

	TestSessionService testSessionService;

	public TestSessionController(TestSessionService testSessionService) {
		this.testSessionService = testSessionService;
	}

	@PostMapping
	public ResponseEntity<?> startTestSession(@RequestParam byte attempts, @RequestParam Long userId, @RequestParam Long testId) {

		try {
			testSessionService.startTestSession(attempts, userId, testId);
			return ResponseEntity.ok("session was starting");
		}catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (TestDeactivatedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> addResponseToSession(Long testId, Long questionId, Long answerId, Long studentId) {
		try {
			testSessionService.addResponse(testId, questionId, answerId, studentId);
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
			testSessionService.setResponse(testId, questionId, answerId, studentId);
			return ResponseEntity.ok("answer was set");
		}catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
//
//	@PostMapping("/complete")
//	public ResponseEntity<?> completeTestSession(Long sessionId) {
//		testSessionService.completeTestSession(sessionId);
//		return ResponseEntity.ok().build();
//	}


}
