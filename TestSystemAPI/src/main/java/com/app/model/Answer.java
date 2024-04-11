package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"answer\"")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String answerText;
	
	private boolean isCorrect;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question questionId;
	
	public Answer() {}
	
	public Answer(String answerText, boolean isCorrect, Question questionId) {
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.questionId = questionId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public boolean getIsCorrect() {
		return isCorrect;
	}
	
	public void setQuestion(Question questionId) {
		this.questionId = questionId;
	}
	
	
	
	
	
	
}
