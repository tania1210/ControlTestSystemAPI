package com.app.dto;

import com.app.model.Test;
import com.app.model.TypeOfQuestion;

public class QuestionDTO {

	private String text;
	private TypeOfQuestion type;

	public QuestionDTO(String text, byte score, TypeOfQuestion typeId) {
		this.text = text;
		this.type = typeId;
	}
	
	public String getText() {
		return text;
	}
	
	public TypeOfQuestion getType() {
		return type;
	}

	
}
