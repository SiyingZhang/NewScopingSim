package edu.scopingsim.bean;

import edu.scopingsim.dao.EventDao;

public class Event {
	private int eventId;
	private int videoId;
	private int x;
	private int y;
	private String timeIndex;
	
	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}
	
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
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

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	
	//Add event to Database
	public int addEvent(int videoid, String timeindex, int x, int y) {
		this.videoId = videoid;
		this.timeIndex = timeindex;
		this.x = x;
		this.y = y;
		
		EventDao ed = new EventDao();
		this.eventId = ed.insertEvent(videoid, timeIndex, x, y);
		
		return eventId;
	}
	
	
}
