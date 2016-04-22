package edu.scopingsim.bean;

import java.util.ArrayList;
import java.util.UUID;

public class Quiz {
	
	private UUID quizId;
	private UUID belongTo;
	private String quizType;
	private String quizText;
	private String timeIndex;  	//TODO: find the exact type of this variable
	private ArrayList<Choice> choices;
	
	public Quiz() {
		// TODO Auto-generated constructor stub
	}
	
	public UUID getQuizId() {
		return this.quizId;
	}
	
	public void setQuizId(UUID id) {
		this.quizId = id;
	}
	
	public UUID getBelongTo() {
		return this.belongTo;
	}
	
	public void setBelongTo(UUID id) {
		this.belongTo = id;
	}
	
	public String getQuizType() {
		return this.quizType;
	}
	
	public void setQuizType(String t) {
		this.quizType = t;
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

	/**
	 * @return the timeIndex
	 */
	public String getTimeIndex() {
		return timeIndex;
	}

	/**
	 * @param timeIndex the timeIndex to set
	 */
	public void setTimeIndex(String timeIndex) {
		this.timeIndex = timeIndex;
	}
	
}
