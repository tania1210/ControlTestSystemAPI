package com.app.repository;

import com.app.model.TestActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestActivationRepository extends JpaRepository<TestActivation, Long> {


    @Query("SELECT ta FROM TestActivation ta WHERE ta.testId.id = :testId")
    TestActivation getByTestId(@Param("testId") Long testId);

}
