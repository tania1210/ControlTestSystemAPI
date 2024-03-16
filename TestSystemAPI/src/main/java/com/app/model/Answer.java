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
	
	public Answer(String answerText, boolean isCorrect) {
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}
	
	
	
	
	
	
}
