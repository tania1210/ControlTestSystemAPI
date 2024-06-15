package com.app.repository;

import com.app.model.Student;
import com.app.model.StudentTestAttemptsRemaining;
import com.app.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentTestAttemptsRemainingRepository extends JpaRepository<StudentTestAttemptsRemaining, Long> {

    @Query("SELECT sta FROM StudentTestAttemptsRemaining sta WHERE sta.testId.id = :testId AND sta.studentId.id = :studentId")
    Optional<StudentTestAttemptsRemaining> findByTestIdAndStudentId(@Param("testId") Long testId, @Param("studentId") Long studentId);
}
