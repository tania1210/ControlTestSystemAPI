package com.app.controller;

import com.app.model.Test;
import com.app.model.TestSession;
import com.app.service.TestSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@AllArgsConstructor
@RequestMapping("/test-session")
public class TestSessionController {
	TestSession testSession;
	TestSessionService testSessionService;

	public TestSessionController() {
		this.testSessionService = new TestSessionService();
	}

	@PostMapping
	public ResponseEntity<Long> startTestSession(Long userId, Test testId) {
		Long sessionId = testSessionService.startTestSession(userId, testId);
		return ResponseEntity.ok(sessionId);
	}

	@PostMapping("/add-response")
	public ResponseEntity<?> addResponseToSession(Long sessionId, Long questionId, String response) {
		testSessionService.addResponseToSession(sessionId, questionId, response);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/complete")
	public ResponseEntity<?> completeTestSession(Long sessionId) {
		testSessionService.completeTestSession(sessionId);
		return ResponseEntity.ok().build();
	}


}
