package edu.scopingsim.bean;

import java.util.UUID;

public class Choice {
	private int choiceId;
	private int quizId;
	private String choiceText;
	private boolean isTrue;
	
	public int getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}
	public boolean getIsTrue() {
		return isTrue;
	}
	public void setTrue(int n) {
		if (n==0) {
			this.isTrue = false;
		} else {
			this.isTrue = true;
		}
	}
	public String getChoiceText() {
		return choiceText;
	}
	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	
}
