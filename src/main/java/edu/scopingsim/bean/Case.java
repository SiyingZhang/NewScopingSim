package edu.scopingsim.bean;

import java.util.ArrayList;

public class Case {
	
	private int caseId;
	private String caseName;
	private String caseDescription;
	private int createdBy;
	private ArrayList<Video> videoList;
	
	public Case() {
		videoList = new ArrayList<>();
	}
	
	public void setCaseId(int id) {
		this.caseId = id;
	}
	
	public int getCaseId() {
		return this.caseId;
	}
	
	public void setCreatedBy (int createdBy) {
		this.createdBy = createdBy;
	}
	
	public int getCreatedBy() {
		return this.createdBy;
	}
	
	public void addVideo(Video v) {
		videoList.add(v);
	}
	
	//remove the latest one.
	public void removeVideo() {
		videoList.remove(videoList.size()-1);
	}
	
	public ArrayList<Video> getVideoList() {
		return this.videoList;
	}

	/**
	 * @return the caseName
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * @param caseName the caseName to set
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	/**
	 * @return the caseDescription
	 */
	public String getCaseDescription() {
		return caseDescription;
	}

	/**
	 * @param caseDescription the caseDescription to set
	 */
	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}
 
}
