package edu.scopingsim.bean;

import java.util.ArrayList;
import java.util.UUID;

public class Quiz {
	
	private int quizId;
	private int belongTo;
	private String quizType;
	private String quizText;
	private String timeIndex;  	//TODO: find the exact type of this variable
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
	
	public int getBelongTo() {
		return this.belongTo;
	}
	
	public void setBelongTo(int id) {
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
