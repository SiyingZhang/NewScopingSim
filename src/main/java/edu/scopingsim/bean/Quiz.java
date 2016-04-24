package edu.scopingsim.bean;

import java.util.ArrayList;
import java.util.UUID;

public class Quiz {
	
	private int quizId;
	private int event;
	private int quizType;
	private String quizText;
	private ArrayList<Choice> choices;
	
	public Quiz() {
		// TODO Auto-generated constructor stub
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public void setQuizId(int id) {
		this.quizId = id;
	}
	
	public String getQuizText() {
		return quizText;
	}
	
	public void setQuizText(String s) {
		this.quizText = s;
	}
	
	public void addChoice(Choice c) {
		choices.add(c);
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public int getQuizType() {
		return quizType;
	}

	public void setQuizType(int quizType) {
		this.quizType = quizType;
	}

	
	
}
