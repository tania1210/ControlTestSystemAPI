package com.app.service.session;

import com.app.model.*;
import com.app.repository.*;
import exceptions.StudentAnswerAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAnswerService {

    private StudentRepository studentRepository;
    private TestRepository testRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private StudentAnswerRepository studentAnswerRepository;

    public StudentAnswerService(StudentRepository studentRepository, TestRepository testRepository, StudentAnswerRepository studentAnswerRepository,
                                QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.studentRepository = studentRepository;
        this.testRepository = testRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public void addResponse(Long testId, Long questionId, Long answerId, Long studentId) throws EntityNotFoundException {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()) {
            throw new EntityNotFoundException(String.format("student hasn't found with id: %d", studentId));
        }else {
            System.out.println("student is present: " + student.get().getId());
            Optional<Test> test = testRepository.findById(testId);
            if(test.isEmpty()) {
                throw new EntityNotFoundException(String.format("test hasn't found with id: %d", testId));
            }else {
                System.out.println("test is present: " + test.get().getId());
                Optional<Question> question = questionRepository.findById(questionId);
                if(question.isEmpty()) {
                    throw new EntityNotFoundException(String.format("question hasn't found with id: %d", questionId));
                }else {
                    System.out.println("question is present: " + question.get().getId());
                    Optional<Answer> answer = answerRepository.findById(answerId);
                    if(answer.isEmpty()) {
                        throw new EntityNotFoundException(String.format("answer hasn't found with id: %d", answerId));
                    } else {
                        System.out.println("answer is present: " + answer.get().getId());
                        Optional<StudentAnswer> studentAnswer = studentAnswerRepository.findByTestIdAndQuestionIdAndStudentId(testId, questionId, studentId);
                        if(studentAnswer.isPresent()) {
                            throw new StudentAnswerAlreadyExistsException("");
                        }else {
                            System.out.println("student answer is save: ");
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
}
