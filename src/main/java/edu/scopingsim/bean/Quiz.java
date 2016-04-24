package edu.scopingsim.bean;

import java.util.ArrayList;

import edu.scopingsim.dao.QuizDao;

public class Quiz {
	
	private int quizId;
	private int eventId;
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

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventid) {
		this.eventId = eventid;
	}

	public int getQuizType() {
		return quizType;
	}

	public void setQuizType(int quizType) {
		this.quizType = quizType;
	}
	
	/**
	 * Insert quiz into database
	 * @param eventid
	 * @param type
	 * @param text
	 * @return
	 */
	public int addQuiz(int eventid, int type, String text) {
		this.eventId = eventid;
		this.quizType = type;
		this.quizText = text;
		
		QuizDao qd = new QuizDao();
		this.quizId = qd.insertQuiz(eventId, quizType, quizText);
		return quizId;
	}	
}
