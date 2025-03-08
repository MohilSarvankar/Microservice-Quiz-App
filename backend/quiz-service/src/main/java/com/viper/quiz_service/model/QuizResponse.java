package com.viper.quiz_service.model;

public class QuizResponse {
	
	private int id;
	private String response;
	
	public QuizResponse(int id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
}
