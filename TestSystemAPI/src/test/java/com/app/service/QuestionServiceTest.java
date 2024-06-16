package com.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.app.model.Question;
import com.app.model.TypeOfQuestion;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.TestRepository;
import com.app.repository.TypeOfQuestionRepository;
import com.app.service.testing.QuestionService;
import exceptions.QuestionAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private TypeOfQuestionRepository typeOfQuestionRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionService(testRepository, questionRepository, typeOfQuestionRepository, answerRepository);
    }

    @Test
    void testCreateNewQuestion_Success() throws QuestionAlreadyExistsException, IllegalArgumentException, EntityNotFoundException {
        when(questionRepository.findByQuestionText(anyString())).thenReturn(Optional.empty());
        when(typeOfQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new TypeOfQuestion()));
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(new com.app.model.Test()));
        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question result = questionService.createNewQuestion("What is Java?", 1L, 1L);

        assertNotNull(result);
        assertEquals("What is Java?", result.getQuestionText());
    }

    @Test
    void testCreateNewQuestion_QuestionAlreadyExistsException() {
        when(questionRepository.findByQuestionText(anyString())).thenReturn(Optional.of(new Question()));

        assertThrows(QuestionAlreadyExistsException.class, () -> questionService.createNewQuestion("What is Java?", 1L, 1L));
    }

    @Test
    void testCreateNewQuestion_EntityNotFoundException_TestNotFound() {
        when(questionRepository.findByQuestionText(anyString())).thenReturn(Optional.empty());
        when(typeOfQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new TypeOfQuestion()));
        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionService.createNewQuestion("What is Java?", 1L, 1L));
    }

    @Test
    void testCreateNewQuestion_IllegalArgumentException_TypeNotFound() {
        when(questionRepository.findByQuestionText(anyString())).thenReturn(Optional.empty());
        when(typeOfQuestionRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(new com.app.model.Test()));

        assertThrows(IllegalArgumentException.class, () -> questionService.createNewQuestion("What is Java?", 1L, 1L));
    }

    @Test
    void testSetQuestionDatas_Success() throws EntityNotFoundException {
        Question question = new Question();
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(questionRepository.getById(anyLong())).thenReturn(question);
        when(typeOfQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new TypeOfQuestion()));
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(new com.app.model.Test()));
        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question result = questionService.setQuestionDatas(1L, "What is Java?", 1L, 1L);

        assertNotNull(result);
        assertEquals("What is Java?", result.getQuestionText());
    }

    @Test
    void testSetQuestionDatas_EntityNotFoundException_QuestionNotFound() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionService.setQuestionDatas(1L, "What is Java?", 1L, 1L));
    }

    @Test
    void testSetQuestionDatas_EntityNotFoundException_TypeNotFound() {
        Question question = new Question();
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(questionRepository.getById(anyLong())).thenReturn(question);
        when(typeOfQuestionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionService.setQuestionDatas(1L, "What is Java?", 1L, 1L));
    }

    @Test
    void testSetQuestionDatas_EntityNotFoundException_TestNotFound() {
        Question question = new Question();
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(questionRepository.getById(anyLong())).thenReturn(question);
        when(typeOfQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new TypeOfQuestion()));
        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionService.setQuestionDatas(1L, "What is Java?", 1L, 1L));
    }

    @Test
    void testDeleteQuestion_Success() throws EntityNotFoundException {
        Question question = new Question();
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestionId(any(Question.class))).thenReturn(Collections.emptyList());

        questionService.deleteQuestion(1L);

        verify(questionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteQuestion_EntityNotFoundException() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionService.deleteQuestion(1L));
    }

    @Test
    void testFetchQuestions_Success() throws EntityNotFoundException {
        com.app.model.Test test = new com.app.model.Test();
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(test));
        when(questionRepository.findAllQuestionByTestId(anyLong())).thenReturn(Collections.singletonList(new Question()));

        List<Question> questions = questionService.fetchQuestions(1L);

        assertFalse(questions.isEmpty());
    }

    @Test
    void testFetchQuestions_EntityNotFoundException() {
        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionService.fetchQuestions(1L));
    }
}

