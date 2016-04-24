package edu.scopingsim.bean;

import java.util.UUID;

public class Note {
	
	private int noteId;
	private String noteText;
	private int eventId;
	
	/**
	 * @return the noteId
	 */
	public int getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	/**
	 * @return the noteText
	 */
	public String getNoteText() {
		return noteText;
	}
	/**
	 * @param noteText the noteText to set
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	

}
