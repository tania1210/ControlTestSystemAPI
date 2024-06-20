package com.app.controller.session;

import com.app.model.Test;
import com.app.service.session.TestSessionService;
import exceptions.StudentAnswerAlreadyExistsException;
import exceptions.TestDeactivatedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "створити сеанс тестування", description = "запускає сеанс тестування")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "сеанс створено та запущено"),
			@ApiResponse(responseCode = "400", description = "студент немає спроб для проходження тесту"),
			@ApiResponse(responseCode = "404", description = "сутність тест/користувач не знайдено"),
			@ApiResponse(responseCode = "409", description = "тест уже недоступний для проходження")
	})
	@PostMapping
	public ResponseEntity<?> startTestSession(@RequestParam Long studentId, @RequestParam Long testId) {

		try {
			testSessionService.startTestSession(studentId, testId);
			return ResponseEntity.ok("session was starting");
		}catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (TestDeactivatedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}


//
//	@PostMapping("/complete")
//	public ResponseEntity<?> completeTestSession(Long sessionId) {
//		testSessionService.completeTestSession(sessionId);
//		return ResponseEntity.ok().build();
//	}


}
