package com.app.repository;

import com.app.model.Group;
import com.app.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject save(Subject subject);
    Subject getById(Long id);
    void deleteById(Long id);

    @Query("SELECT s FROM Subject s WHERE s.userId.id = :userId")
    List<Subject> findAllSubjectByUserId(@Param("userId") Long Userid);
//    Subject findSubjectByGroupId(Group group);

}
