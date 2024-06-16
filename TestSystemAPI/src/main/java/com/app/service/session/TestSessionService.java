package com.app.service.session;

import com.app.model.*;
import com.app.repository.*;
import exceptions.StudentAnswerAlreadyExistsException;
import exceptions.TestDeactivatedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TestSessionService {

   private TestSessionRepository testSessionRepository;
   private StudentRepository studentRepository;
   private TestRepository testRepository;
   private StudentTestAttemptsRemainingRepository studentTestAttemptsRemainingRepository;

   public TestSessionService(TestSessionRepository testSessionRepository, StudentRepository studentRepository, TestRepository testRepository,
                             StudentTestAttemptsRemainingRepository studentTestAttemptsRemainingRepository) {
      this.testSessionRepository = testSessionRepository;
      this.studentRepository = studentRepository;
      this.testRepository = testRepository;
      this.studentTestAttemptsRemainingRepository = studentTestAttemptsRemainingRepository;
   }

   public void startTestSession(byte studentAttempts, Long studentId, Long testId)
           throws EntityNotFoundException, TestDeactivatedException, IllegalStateException {
       Optional<Student> student = studentRepository.findById(studentId);
       if (student.isEmpty()) {
          throw new EntityNotFoundException(String.format("student hasn't found with id: %studentId", studentId));
       }else {
          Optional<Test> test = testRepository.findById(testId);
          if(test.isEmpty()) {
             throw new EntityNotFoundException(String.format("test hasn't found with id: %testId", testId));
          }else {
             if(!test.get().getActivate()) {
                throw new TestDeactivatedException("test's already deactivated");
             }else {
                if(studentAttempts == 0) {
                  throw new IllegalStateException("Student has no attempts left on this test");
                }else {
                   Optional<StudentTestAttemptsRemaining> studentTestAttemptsRemaining = studentTestAttemptsRemainingRepository.findByTestIdAndStudentId(testId, studentId);
                   studentTestAttemptsRemaining.get().setStudentAttempts((byte) (studentTestAttemptsRemaining.get().getStudentAttempts()-1));
                   studentTestAttemptsRemainingRepository.save(studentTestAttemptsRemaining.get());
                   LocalDateTime startTime = LocalDateTime.now();
                   LocalDateTime endTime = LocalDateTime.of(startTime.toLocalDate(), test.get().getDuraction().toLocalTime());
                   testSessionRepository.save(new TestSession(startTime, endTime, test.get(), student.get()));
                }
             }
          }
       }
   }



}
