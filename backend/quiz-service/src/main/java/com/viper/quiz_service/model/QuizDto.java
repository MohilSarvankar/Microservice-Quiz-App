package com.viper.quiz_service.model;

public class QuizDto {
	private String category;
	private int noOfQuestions;
	private String title;
	
	public QuizDto() {
		
	}
	
	public QuizDto(String category, int noOfQuestions, String title) {
		super();
		this.category = category;
		this.noOfQuestions = noOfQuestions;
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "QuizDto [category=" + category + ", noOfQuestions=" + noOfQuestions + ", title=" + title + "]";
	}
	
}
