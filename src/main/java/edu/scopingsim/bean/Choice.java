package edu.scopingsim.bean;

import java.util.UUID;

public class Choice {
	private UUID choiceId;
	private String choiceText;
	private boolean isTrue;
	
	public UUID getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(UUID choiceId) {
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
	
	
}
