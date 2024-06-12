package com.app.model;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"test\"")
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private Time duraction;
	
	private byte attempts; //к-сть спроб
	
	@Column(columnDefinition = "boolean default false")
	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject subjectId;
	
	private byte fullScore;
	
//	@Column(columnDefinition = "boolean default false")
//	private boolean completed;
	
//	@ManyToOne
//	@JoinColumn(name = "created_by_id", referencedColumnName = "id")
//	private User createdBy;
	
	public Test() {}
	
	public Test(String name, Time duraction, byte attempts, byte fullScore) {
		this.name = name;
		this.duraction = duraction;
		this.attempts = attempts;
		this.fullScore = fullScore;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getDuraction() {
		return duraction;
	}

	public void setDuraction(Time duraction) {
		this.duraction = duraction;
	}

	public byte getFullScore() {
		return fullScore;
	}

	public void setFullScore(byte fullScore) {
		this.fullScore = fullScore;
	}

	public byte getAttemps() {
		return attempts;
	}

	public void setAttemps(byte attempts) {
		this.attempts = attempts;
	}
	
	
	
	
	
	
}
