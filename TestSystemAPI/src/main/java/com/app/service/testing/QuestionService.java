package com.app.service.testing;

import com.app.model.Answer;
import com.app.model.Question;
import com.app.model.Test;
import com.app.model.TypeOfQuestion;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.TestRepository;
import com.app.repository.TypeOfQuestionRepository;
import exceptions.QuestionAlreadyExistsException;
import exceptions.TestAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private TestRepository testRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private TypeOfQuestionRepository typeOfQuestionRepository;

    public QuestionService(TestRepository testRepository, QuestionRepository questionRepository, TypeOfQuestionRepository typeOfQuestionRepository, AnswerRepository answerRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.typeOfQuestionRepository = typeOfQuestionRepository;
        this.answerRepository = answerRepository;
    }

    public Question createNewQuestion(String questionText, Long typeId, Long testId) throws QuestionAlreadyExistsException, EntityNotFoundException {
        Optional<Question> existingQuestion = questionRepository.findByQuestionText(questionText);
        if(existingQuestion.isPresent()) {
           throw new QuestionAlreadyExistsException("this question is already exists");
        }else {
            Optional<TypeOfQuestion> existingtype = typeOfQuestionRepository.findById(typeId);
            if(!testRepository.findById(testId).isPresent()){
                throw new EntityNotFoundException("test not found");
            }if(!existingtype.isPresent()) {
                throw new EntityNotFoundException("this question type not found");
            }else {
                Question question = questionRepository.save(new Question(questionText, existingtype.get(), testRepository.getById(testId)));
                return question;
            }
        }
    }

    public Question setQuestionDatas(Long id, String questionText, Long typeId, Long testId) throws EntityNotFoundException{
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if(!existingQuestion.isPresent()) {
            throw new EntityNotFoundException("question not found. No data's been changed");
        }else {
            Question question = questionRepository.getById(id);
            if(questionText != null) {
                question.setQuestionText(questionText);
            }if(typeId != 0) {
                Optional<TypeOfQuestion> existingtype = typeOfQuestionRepository.findById(typeId);
                if(existingtype.isPresent()) {
                    question.setTypeId(existingtype.get());
                }else {
                    throw new EntityNotFoundException("new type of question hasn't found");
                }
            }if(testId != 0) {
                Optional<Test> existingTest = testRepository.findById(testId);
                if(existingTest.isPresent()) {
                    question.setTestId(existingTest.get());
                }else {
                    throw new EntityNotFoundException("new test of this question hasn't found");
                }
            }
            Question question1 = questionRepository.save(question);
            System.out.println(question1);
            return question1;
        }
    }

    public void deleteQuestion(Long id) throws EntityNotFoundException{
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if(!existingQuestion.isPresent()) {
            throw new EntityNotFoundException("question hasn't found");
        }else {
            List<Answer> answers = answerRepository.findByQuestionId(existingQuestion.get());

            for (Answer answer : answers) {
                System.out.println(answer.getId());
                answerRepository.deleteById(answer.getId());
            }
            questionRepository.deleteById(id);
        }

    }

    public List<Question> fetchQuestions(Long id) throws EntityNotFoundException {
        Optional<Test> existingTest = testRepository.findById(id);
        if(existingTest.isEmpty()) {
            throw new EntityNotFoundException("test not exists. Wrong id");
        }else {
            return questionRepository.findAllQuestionByTestId(id);
        }

    }
}
