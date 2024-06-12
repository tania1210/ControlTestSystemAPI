package com.app.repository;

import com.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getById(Long id);

    @Query("SELECT s FROM Student s WHERE s.userId.id = :userId")
    Student findStudentByUserId(@Param("userId") Long userId);

}
