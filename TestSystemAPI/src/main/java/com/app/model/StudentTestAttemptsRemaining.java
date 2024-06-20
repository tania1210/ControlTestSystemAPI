package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"student_test_attempts_remaining\"")
public class StudentTestAttemptsRemaining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    byte studentAttempts;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    Test testId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    Student studentId;

    public StudentTestAttemptsRemaining() {}

    public StudentTestAttemptsRemaining(byte studentAttempts, Test testId, Student studentId) {
        this.studentAttempts = studentAttempts;
        this.studentId = studentId;
        this.testId = testId;
    }

    public void setStudentAttempts(byte studentAttempts) {
        this.studentAttempts = studentAttempts;
    }

    public byte getStudentAttempts() {
        return studentAttempts;
    }

    public Test getTestId() {
        return testId;
    }

    public Student getStudentId() {
        return studentId;
    }

}
