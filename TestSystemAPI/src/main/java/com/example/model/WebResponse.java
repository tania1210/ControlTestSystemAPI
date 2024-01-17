package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"code", "status", "message"})
public class WebResponse {
	
	@JsonProperty("code")
	private Integer code;
	   
	@JsonProperty("status")
	private String status;
	   
	@JsonProperty("message")
	private String message;

	
	
}
