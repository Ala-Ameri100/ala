package com.learn.ala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {

	private long id;
	private String topic;
	private String difficultyLevel;
	private String correctAnswer;
	private String answerChoice1;
	private String answerChoice2;
	private String answerChoice3;
	private String answerChoice4;
	private String answerChoice5;
	private String answerChoice6;
	private String answerChoice7;
	private String answerDescription;
	private String questionText;
	private String action;
	private String questionId;
	private String questionTag;
	private Boolean clickableTopic;
	
	
	
	
	public Questions() {
		
	}
	
	
	
	public Questions(long id, String topic, String difficultyLevel, String correctAnswer, String answerChoice1,
			String answerChoice2, String answerChoice3, String answerChoice4, String answerChoice5, String answerChoice6,
			String answerChoice7, String answerDescription, String questionText, String action, String questionId,
			String questionTag) {
		super();
		this.id = id;
		this.topic = topic;
		this.difficultyLevel = difficultyLevel;
		this.correctAnswer = correctAnswer;
		this.answerChoice1 = answerChoice1;
		this.answerChoice2 = answerChoice2;
		this.answerChoice3 = answerChoice3;
		this.answerChoice4 = answerChoice4;
		this.answerChoice5 = answerChoice5;
		this.answerChoice6 = answerChoice6;
		this.answerChoice7 = answerChoice7;
		this.answerDescription = answerDescription;
		this.questionText = questionText;
		this.action = action;
		this.questionId = questionId;
		this.questionTag = questionTag;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "topic", nullable = false)
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Column(name = "difficulty_level", nullable = false)
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	@Column(name = "question_text", nullable = false)
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	@Column(name = "question_tag")
	public String getQuestionTag() {
		return questionTag;
	}
	public void setQuestionTag(String questionTag) {
		this.questionTag = questionTag;
	}
	@Column(name = "correct_answer")
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	@Column(name = "answer_choice_1")
	public String getAnswerChoice1() {
		return answerChoice1;
	}
	public void setAnswerChoice1(String answerChoice1) {
		this.answerChoice1 = answerChoice1;
	}
	@Column(name = "answer_choice_2")
	public String getAnswerChoice2() {
		return answerChoice2;
	}
	public void setAnswerChoice2(String answerChoice2) {
		this.answerChoice2 = answerChoice2;
	}
	@Column(name = "answer_choice_3")
	public String getAnswerChoice3() {
		return answerChoice3;
	}
	public void setAnswerChoice3(String answerChoice3) {
		this.answerChoice3 = answerChoice3;
	}
	@Column(name = "answer_choice_4")
	public String getAnswerChoice4() {
		return answerChoice4;
	}
	public void setAnswerChoice4(String answerChoice4) {
		this.answerChoice4 = answerChoice4;
	}
	@Column(name = "answer_choice_5")
	public String getAnswerChoice5() {
		return answerChoice5;
	}
	public void setAnswerChoice5(String answerChoice5) {
		this.answerChoice5 = answerChoice5;
	}
	@Column(name = "answer_choice_6")
	public String getAnswerChoice6() {
		return answerChoice6;
	}
	public void setAnswerChoice6(String answerChoice6) {
		this.answerChoice6 = answerChoice6;
	}
	@Column(name = "answer_choice_7")
	public String getAnswerChoice7() {
		return answerChoice7;
	}
	public void setAnswerChoice7(String answerChoice7) {
		this.answerChoice7 = answerChoice7;
	}
	@Column(name = "answer_description")
	public String getAnswerDescription() {
		return answerDescription;
	}
	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
	}
	@Column(name = "action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Column(name = "question_id")
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "clickable_topic")	
	public Boolean getClickableTopic() {
		return clickableTopic;
	}

	public void setClickableTopic(Boolean clickableTopic) {
		this.clickableTopic = clickableTopic;
	}



	@Override
	public String toString() {
		return "Questions [id=" + id + ", topic=" + topic + ", difficultyLevel=" + difficultyLevel + ", correctAnswer="
				+ correctAnswer + ", answerChoice1=" + answerChoice1 + ", answerChoice2=" + answerChoice2
				+ ", answerChoice3=" + answerChoice3 + ", answerChoice4=" + answerChoice4 + ", answerChoice5=" + answerChoice5
				+ ", answerChoice6=" + answerChoice6 + ", answerChoice7=" + answerChoice7 + ", answerDescription="
				+ answerDescription + ", questionText=" + questionText + ", action=" + action + ", questionId="
				+ questionId + ", questionTag=" + questionTag + "]";
	}
	
	
}
	
	

