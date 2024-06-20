package com.app.repository;

import com.app.model.StudentAnswer;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    @Query("SELECT sa FROM StudentAnswer sa WHERE sa.testId.id = :testId AND sa.questionId.id = :questionId AND studentId.id = :studentId")
    Optional<StudentAnswer> findByTestIdAndQuestionIdAndStudentId(@Param("testId") Long testId, @Param("questionId") Long questionId, @Param("studentId") Long studentId);

    @Query("SELECT sa FROM StudentAnswer sa WHERE sa.testId.id = :testId AND sa.studentId.id = :studentId")
    List<StudentAnswer> getByTestIdAndStudentId(@Param("testId") Long testId, @Param("studentId") Long studentId);
}
