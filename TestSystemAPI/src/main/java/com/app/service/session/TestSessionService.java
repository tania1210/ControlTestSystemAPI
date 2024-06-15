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
   private QuestionRepository questionRepository;
   private AnswerRepository answerRepository;
   private StudentTestAttemptsRemainingRepository studentTestAttemptsRemainingRepository;
   private StudentAnswerRepository studentAnswerRepository;

   public TestSessionService(TestSessionRepository testSessionRepository, StudentRepository studentRepository, TestRepository testRepository,
                             StudentTestAttemptsRemainingRepository studentTestAttemptsRemainingRepository, StudentAnswerRepository studentAnswerRepository,
                             QuestionRepository questionRepository, AnswerRepository answerRepository) {
      this.testSessionRepository = testSessionRepository;
      this.studentRepository = studentRepository;
      this.testRepository = testRepository;
      this.studentTestAttemptsRemainingRepository = studentTestAttemptsRemainingRepository;
      this.studentAnswerRepository = studentAnswerRepository;
      this.questionRepository = questionRepository;
      this.answerRepository = answerRepository;
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

   public void addResponse(Long testId, Long questionId, Long answerId, Long studentId) throws EntityNotFoundException{
      Optional<Student> student = studentRepository.findById(studentId);
      if(student.isEmpty()) {
         throw new EntityNotFoundException(String.format("student hasn't found with id: %d", studentId));
      }else {
         Optional<Test> test = testRepository.findById(testId);
         if(test.isEmpty()) {
            throw new EntityNotFoundException(String.format("test hasn't found with id: %d", testId));
         }else {
            Optional<Question> question = questionRepository.findById(questionId);
            if(question.isEmpty()) {
               throw new EntityNotFoundException(String.format("question hasn't found with id: %d", questionId));
            }else {
               Optional<Answer> answer = answerRepository.findById(answerId);
               if(answer.isEmpty()) {
                  throw new EntityNotFoundException(String.format("answer hasn't found with id: %d", answerId));
               } else {
                  Optional<StudentAnswer> studentAnswer = studentAnswerRepository.findByTestIdAndQuestionIdAndStudentId(testId, questionId, studentId);
                  if(studentAnswer.isPresent()) {
                     throw new StudentAnswerAlreadyExistsException("");
                  }else {
                     studentAnswerRepository.save(new StudentAnswer(test.get(), question.get(), answer.get(), student.get()));
                  }
               }
            }
         }
      }
   }

   public void setResponse(Long testId, Long questionId, Long answerId, Long studentId) throws EntityNotFoundException{
      Optional<StudentAnswer> studentAnswer = studentAnswerRepository.findByTestIdAndQuestionIdAndStudentId(testId, questionId, studentId);
      if(studentAnswer.isEmpty()) {
         throw new EntityNotFoundException("this response hasn't found");
      }else {
         Optional<Answer> answer = answerRepository.findById(answerId);
         if(answer.isPresent()) {
            studentAnswer.get().setAnswerId(answer.get());
         }
         studentAnswerRepository.save(studentAnswer.get());
      }
   }

   public void completeTestSession(Long sessionId) {

   }
}
