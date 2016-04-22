package edu.scopingsim.bean;

import java.util.UUID;

public class Event {
	private UUID eventId;
	private int x;
	private int y;
	private String timeIndex;
	
	/**
	 * @return the eventId
	 */
	public UUID getEventId() {
		return eventId;
	}
	
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(UUID eventId) {
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
	
}
