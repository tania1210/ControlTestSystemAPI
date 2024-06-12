package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"student_answer\"")
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answerId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentId;

    public StudentAnswer() {}

    public StudentAnswer(Answer answerId, Student studentId) {
        this.answerId = answerId;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public Answer getAnswerId(){
        return answerId;
    }

    public Student getStudentId() {
        return studentId;
    }
}
