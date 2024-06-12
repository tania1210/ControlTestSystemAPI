package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"test_result\"")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte score;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test testId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentId;

    public TestResult() {}

    public TestResult(byte score, Test testId, Student studentId) {
        this.score = score;
        this.testId = testId;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public byte getScore(){
        return score;
    }

    public Test getTestId() {
        return testId;
    }

    public Student getStudentId() {
        return studentId;
    }

}
