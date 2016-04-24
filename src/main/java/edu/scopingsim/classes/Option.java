package edu.scopingsim.classes;

import java.util.List;

public class Option {

	private String text;
	private boolean isCorrect;

	@Override
	public String toString() {
		return "Option - { " + text + ", " + isCorrect + " }";
	}
}