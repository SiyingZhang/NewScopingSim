package edu.scopingsim.classes;

import java.util.List;

public class Choice {

	private List<Option> options;

	@Override
	public String toString() {
		return "Choice - { " + options + " }";
	}
}