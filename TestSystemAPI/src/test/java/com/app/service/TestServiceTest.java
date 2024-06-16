package com.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.app.model.Student;
//import com.app.model.Test;
import com.app.repository.QuestionRepository;
import com.app.repository.StudentRepository;
import com.app.repository.TestRepository;
import com.app.repository.AnswerRepository;
import com.app.service.testing.TestService;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import exceptions.TestAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    void setUp() {
        testService = new TestService(testRepository, questionRepository, answerRepository, studentRepository);
    }

    @Test
    void testCreateNewTest_Success() throws TestAlreadyExistsException, InvalidFormatException {
        when(testRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(testRepository.saveAndFlush(any(com.app.model.Test.class))).thenAnswer(invocation -> invocation.getArgument(0));

        com.app.model.Test result = testService.createNewTest("Java", "01:00:00", (byte) 3, (byte) 100);

        assertNotNull(result);
        assertEquals("Java", result.getName());
    }

    @Test
    void testCreateNewTest_TestAlreadyExistsException() {
        when(testRepository.findByName(anyString())).thenReturn(Optional.of(new com.app.model.Test()));

        assertThrows(TestAlreadyExistsException.class, () -> testService.createNewTest("Java", "01:00:00", (byte) 3, (byte) 100));
    }

    @Test
    void testCreateNewTest_InvalidFormatException() {
        when(testRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(InvalidFormatException.class, () -> testService.createNewTest("Java", "invalid", (byte) 3, (byte) 100));
    }

    @Test
    void testSetTestDatas_Success() throws InvalidFormatException, EntityNotFoundException {
        com.app.model.Test test = new com.app.model.Test();
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(test));
        when(testRepository.getById(anyLong())).thenReturn(test);

        testService.setTestDatas(1L, "Java", "01:00:00", (byte) 100, (byte) 3);

        assertEquals("Java", test.getName());
        assertEquals(Time.valueOf("01:00:00"), test.getDuraction());
    }

    @Test
    void testSetTestDatas_EntityNotFoundException() {
        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> testService.setTestDatas(1L, "Java", "01:00:00", (byte) 100, (byte) 3));
    }

    @Test
    void testSetTestDatas_InvalidFormatException() {
        com.app.model.Test test = new com.app.model.Test();
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(test));
        when(testRepository.getById(anyLong())).thenReturn(test);

        assertThrows(InvalidFormatException.class, () -> testService.setTestDatas(1L, "Java", "invalid", (byte) 100, (byte) 3));
    }

    @Test
    void testDeleteTest_Success() throws EntityNotFoundException {
        com.app.model.Test test = new com.app.model.Test();
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(test));
        when(questionRepository.findByTestId(any(com.app.model.Test.class))).thenReturn(Collections.emptyList());

        testService.deleteTest(1L);

        verify(testRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTest_EntityNotFoundException() {
        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> testService.deleteTest(1L));
    }

    @Test
    void testFetchTest_Success() throws EntityNotFoundException {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findStudentByUserId(anyLong())).thenReturn(Optional.of(student));
        when(testRepository.findAllTestsByStudentId(anyLong())).thenReturn(Collections.singletonList(new com.app.model.Test()));

        List<com.app.model.Test> tests = testService.fetchTest(1L);

        assertFalse(tests.isEmpty());
    }

    @Test
    void testFetchTest_EntityNotFoundException() {
        when(studentRepository.findStudentByUserId(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> testService.fetchTest(1L));
    }
}

