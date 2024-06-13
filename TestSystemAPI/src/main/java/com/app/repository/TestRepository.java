package com.app.repository;

import java.util.List;
import java.util.Optional;

import com.app.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

    Test save(Test test);
    Test getById(Long id);
//    Test findById(Long id);
    Optional<Test> findByName(String name);
    Optional<Test> findById(Long id);

    void deleteById(Long id);

//    @Query("SELECT t FROM Test t WHERE t.subject.id IN (SELECT g.subject.id FROM Group g WHERE g.id = (SELECT s.group.id FROM Student s WHERE s.id = :studentId))")
//    List<Test> findAllTestsByStudentId(@Param("studentId") Long studentId);
    @Query("SELECT t FROM Test t WHERE t.subjectId.id IN (SELECT g.subjectId.id FROM Group g WHERE g.id = (SELECT s.groupId.id FROM Student s WHERE s.id = :studentId))")
    List<Test> findAllTestsByStudentId(@Param("studentId") Long studentId);

}
