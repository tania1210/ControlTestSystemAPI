package com.app.dto;

import java.sql.Time;

public class TestDTO {
	
	private String name;
	private Time duraction;
	private byte fullScore;
	private byte attemps;
	
	public TestDTO() {}
	
	public TestDTO(String name, Time duraction, byte fullScore, byte attemps) {
		this.setName(name);
		this.setDuraction(duraction);
		this.setFullScore(fullScore);
		this.setAttemps(attemps);
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
		return attemps;
	}

	public void setAttemps(byte attemps) {
		this.attemps = attemps;
	}
	
	

}
