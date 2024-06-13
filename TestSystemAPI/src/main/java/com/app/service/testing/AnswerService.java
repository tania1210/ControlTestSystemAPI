package com.app.service.testing;

import com.app.model.Answer;
import com.app.model.Question;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import exceptions.AnswerAlreadyExistsException;
import exceptions.TestAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public AnswerService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Answer createNewAnswer(String text, boolean isCorrect, Long questionId) throws AnswerAlreadyExistsException, EntityNotFoundException {
        Optional<Answer> existingAnswer = answerRepository.findByAnswerText(text);
        if(existingAnswer.isPresent() && existingAnswer.get().getQuestionId().getId() == questionId) {
            throw new AnswerAlreadyExistsException("this answer is already for this question");
        }else {
            System.out.println("answer not found. The next will search a question");
            Optional<Question> existingQuestion = questionRepository.findById(questionId);
            if(existingQuestion.isEmpty()) {
                throw new EntityNotFoundException("question hasn't found");
            }else {
                return answerRepository.save(new Answer(text, isCorrect, questionRepository.getById(questionId)));
            }
        }
    }

    public Answer setAnswerDatas(Long id, String answerText, boolean isCorrect, Long questionId) throws EntityNotFoundException{
        if(answerRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("answer's not found");
        }else {
            if(questionRepository.findById(questionId).isEmpty()) {
                throw new EntityNotFoundException("new question's not found. Wrong id");
            }else {
                Answer answer = answerRepository.getById(id);
                if(!answerText.equals(answer.getAnswerText())) {
                    answer.setAnswerText(answerText);
                }if(isCorrect != answer.getIsCorrect()) {
                    answer.setIsCorrect(isCorrect);
                }if(questionId != answer.getQuestionId().getId()) {
                    answer.setQuestion(questionRepository.getById(questionId));
                }
                return answerRepository.save(answer);
            }
        }
    }

    public void deleteAnswer(Long id) throws EntityNotFoundException {
        if(answerRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("this answer's not found");
        }else {
            answerRepository.deleteById(id);
        }

    }

    public List<Answer> fetchAnswers(Long id) throws EntityNotFoundException {
        if(questionRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("question's not found. Wrong id");
        }else {
            return answerRepository.findAllByQuestionId(id);
        }

    }
}
