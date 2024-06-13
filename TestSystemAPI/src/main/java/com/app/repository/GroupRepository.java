package com.app.repository;

import com.app.model.Group;
import com.app.model.Question;
import com.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group save(Group group);

    Group getById(Long groupId);

    void deleteById(Long groupId);

    @Query("SELECT g FROM Group g WHERE g.userId.id = :userId")
    List<Group> findAllGroupByUserId(@Param("userId") Long userId);

    Optional<Object> findByName(String name);

//    Group findGroupByStudentId(Student student);

}