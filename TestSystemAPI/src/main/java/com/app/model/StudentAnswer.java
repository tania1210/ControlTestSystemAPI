package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"student_answer\"")
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test testId;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question questionId;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answerId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentId;

    public StudentAnswer() {}

    public StudentAnswer(Test testId, Question questionId, Answer answerId, Student studentId) {
        this.testId = testId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setAnswerId(Answer answerId) {
        this.answerId = answerId;
    }

    public Answer getAnswerId(){
        return answerId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }
}
