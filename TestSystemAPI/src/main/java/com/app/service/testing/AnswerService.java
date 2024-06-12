package com.app.service.testing;

import com.app.model.Answer;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public AnswerService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Long createNewAnswer(String text, boolean isCorrect, Long questionId) throws EntityNotFoundException {

        if(questionRepository.getById(questionId) == null) {
            throw new EntityNotFoundException();
        }else {
            Answer answer = answerRepository.save(new Answer(text, isCorrect, questionRepository.getById(questionId)));
            return answer.getId();
        }

    }

    //SET
    public void setAnswerDatas(Long id, String answerText, boolean isCorrect) throws NullPointerException{
        if(id == null) {
            throw new NullPointerException();
        }else {
            Answer answer = answerRepository.getById(id);
            if(answerText != null) {
                answer.setAnswerText(answerText);
            }if(isCorrect != answer.getIsCorrect()) {
                answer.setIsCorrect(isCorrect);
            }
            answerRepository.save(answer);
        }
    }

    //DELETE
    public void deleteAnswer(Long id) throws EntityNotFoundException{
        answerRepository.deleteById(id);
    }

    public List<Answer> fetchAnswers(Long id) {
        return answerRepository.findAllByQuestionId(id);
    }
}
