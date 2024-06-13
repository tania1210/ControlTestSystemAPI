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
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private TypeOfQuestion typeId;

	@ManyToOne
	@JoinColumn(name = "test_id", referencedColumnName = "id")
	private Test testId;
	
	public Question() {}
	
	public Question(String questionText, TypeOfQuestion typeId, Test testId) {
		this.questionText = questionText;
		this.typeId = typeId;
		this.testId = testId;
	}

	public Long getId() {
		return id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public TypeOfQuestion getTypeId() {
		return typeId;
	}

	public void setTypeId(TypeOfQuestion typeId) {
		this.typeId = typeId;
	}

	public Test getTestId() {
		return testId;
	}

	public void setTestId(Test testId) {
		this.testId = testId;
	}

	
	
	
	
	
	
	
	
	
	
	
}
