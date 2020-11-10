package com.learn.ala.model;

import java.io.Serializable;

public class QuestionAnswerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String question;
	private Options options;

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Options getOptions() {
		return options;
	}
	public void setOptions(Options options) {
		this.options = options;
	}
	
	

}
