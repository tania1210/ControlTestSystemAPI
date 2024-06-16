package com.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.app.model.Answer;
import com.app.model.Question;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import com.app.service.testing.AnswerService;
import exceptions.AnswerAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        answerService = new AnswerService(questionRepository, answerRepository);
    }

    @Test
    void testCreateNewAnswer_Success() throws AnswerAlreadyExistsException, EntityNotFoundException {
        when(answerRepository.findByAnswerText(anyString())).thenReturn(Optional.empty());
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(new Question()));
        when(answerRepository.save(any(Answer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Answer result = answerService.createNewAnswer("Answer text", true, 1L);

        assertNotNull(result);
        assertEquals("Answer text", result.getAnswerText());
        assertTrue(result.getIsCorrect());
    }

    @Test
    void testCreateNewAnswer_EntityNotFoundException() {
        when(answerRepository.findByAnswerText(anyString())).thenReturn(Optional.empty());
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> answerService.createNewAnswer("Answer text", true, 1L));
    }

    @Test
    void testSetAnswerDatas_Success() throws EntityNotFoundException {
        Question question = new Question();
        Answer answer = new Answer("Old text", false, question);
        when(answerRepository.findById(anyLong())).thenReturn(Optional.of(answer));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(answerRepository.getById(anyLong())).thenReturn(answer);
        when(questionRepository.getById(anyLong())).thenReturn(question);
        when(answerRepository.save(any(Answer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Answer result = answerService.setAnswerDatas(1L, "New text", true, 1L);

        assertNotNull(result);
        assertEquals("New text", result.getAnswerText());
        assertTrue(result.getIsCorrect());
    }

    @Test
    void testSetAnswerDatas_EntityNotFoundException_AnswerNotFound() {
        when(answerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> answerService.setAnswerDatas(1L, "New text", true, 1L));
    }

    @Test
    void testSetAnswerDatas_EntityNotFoundException_QuestionNotFound() {
        Question question = new Question();
        Answer answer = new Answer("Old text", false, question);
        when(answerRepository.findById(anyLong())).thenReturn(Optional.of(answer));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> answerService.setAnswerDatas(1L, "New text", true, 1L));
    }

    @Test
    void testDeleteAnswer_Success() throws EntityNotFoundException {
        when(answerRepository.findById(anyLong())).thenReturn(Optional.of(new Answer()));

        answerService.deleteAnswer(1L);

        verify(answerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAnswer_EntityNotFoundException() {
        when(answerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> answerService.deleteAnswer(1L));
    }

    @Test
    void testFetchAnswers_Success() throws EntityNotFoundException {
        Question question = new Question();
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(answerRepository.findAllByQuestionId(anyLong())).thenReturn(Collections.singletonList(new Answer()));

        List<Answer> answers = answerService.fetchAnswers(1L);

        assertFalse(answers.isEmpty());
    }

    @Test
    void testFetchAnswers_EntityNotFoundException() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> answerService.fetchAnswers(1L));
    }
}

