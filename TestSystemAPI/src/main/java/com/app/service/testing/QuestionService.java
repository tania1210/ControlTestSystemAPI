package com.app.service.testing;

import com.app.model.Answer;
import com.app.model.Question;
import com.app.model.Test;
import com.app.model.TypeOfQuestion;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.TestRepository;
import com.app.repository.TypeOfQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Long createNewQuestion(String text, String typeN, Long testId) throws IllegalArgumentException, EntityNotFoundException {
        TypeOfQuestion type = typeOfQuestionRepository.findByName(typeN);
        if(text.isEmpty() || type == null) {
            throw new IllegalArgumentException();
        }else if(testRepository.getById(testId) == null){
            throw new EntityNotFoundException();
        }else {
            System.out.println("class of question is = " + questionRepository.getById((long) 8).getClass());
            Question question = questionRepository.save(new Question(text, type, testRepository.getById(testId)));//test.orElseThrow(() -> new IllegalArgumentException()
            return question.getId();
        }

    }

    //SET
    public void setQuestionDatas(Long id, String questionText, String typeS, Long testId) throws EntityNotFoundException, NullPointerException{
        if(id == null) {
            throw new NullPointerException();
        }else {
            Question question = questionRepository.getById(id);
            if(questionText != null) {
                question.setQuestionText(questionText);
            }if(typeS != null) {
                TypeOfQuestion type = typeOfQuestionRepository.findByName(typeS);
                question.setType(type);
            }if(testId != null) {
                Test test = testRepository.getById(testId);
                question.setTest(test);
            }
            questionRepository.save(question);
        }

    }

    //DELETE
    public void deleteQuestion(Long id) throws EntityNotFoundException{
        Question question = questionRepository.getById(id);

        List<Answer> answers = answerRepository.findByQuestionId(question);

        for (Answer answer : answers) {
            System.out.println(answer.getId());
            answerRepository.deleteById(answer.getId());
        }

        questionRepository.deleteById(id);
    }

    public List<Question> fetchQuestions(Long id) {
        return questionRepository.findAllQuestionByTestId(id);
    }
}
