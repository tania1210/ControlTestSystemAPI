package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"question\"")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "question_text")
	private String questionText;
	
	private byte score;
	

	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private TypeOfQuestion typeId;

	@ManyToOne
	@JoinColumn(name = "test_id", referencedColumnName = "id")
	private Test testId;
	
	public Question() {}
	
	public Question(String questionText, byte score, TypeOfQuestion typeId, Test testId) {
		this.questionText = questionText;
		this.score = score;
		this.typeId = typeId;
		this.testId = testId;
	}

	
	
	
	
	
	
	
	
	
	
	
}
