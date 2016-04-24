package edu.scopingsim.bean;

import edu.scopingsim.dao.NoteDao;

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
	
	/**
	 * Add note to database
	 * @param eventid
	 * @param notetext
	 * @return
	 */
	public int addNote(int eventid, String notetext) {
		this.eventId = eventid;
		this.noteText = notetext;
		
		NoteDao nd = new NoteDao();
		this.noteId = nd.insertNote(eventId, noteText);
		
		return noteId;
	}
	
}
