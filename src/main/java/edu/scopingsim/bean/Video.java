package edu.scopingsim.bean;

import java.util.ArrayList;

import edu.scopingsim.dao.VideoDao;

public class Video {
	
	private int videoId;
	private int caseId;
	private String videoName;
	private String path;
	private ArrayList<Note> noteList;
	private ArrayList<Quiz> quizList;
	
	
	public Video() {
		// TODO Auto-generated constructor stub
		noteList = new ArrayList<Note>();
		quizList = new ArrayList<Quiz>();
	}
	
	/**
	 * @return the videoId
	 */
	public int getVideoId() {
		return videoId;
	}
	/**
	 * @param videoId the videoId to set
	 */
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the videoId
	 */
	public int getcaseId() {
		return caseId;
	}
	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the videoName
	 */
	public String getVideoName() {
		return videoName;
	}

	/**
	 * @param videoName the videoName to set
	 */
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
	/**
	 * @return the noteList
	 */
	public ArrayList<Note> getNoteList() {
		return noteList;
	}
	/**
	 * @param noteList the noteList to set
	 */
	public void addNote(Note note) {
		this.noteList.add(note);
	}
	
	/**
	 * @return the quizList
	 */
	public ArrayList<Quiz> getQuizList() {
		return quizList;
	}
	/**
	 * @param quizList the quizList to set
	 */
	public void addQuiz(Quiz quiz) {
		this.quizList.add(quiz);
	}
	/**
	 * Add video to Database
	 * @param caseid
	 * @param videoname
	 * @param p
	 * @return insert record ID
	 */
	public int addVideo(int caseid, String videoname, String p) {
		VideoDao vd = new VideoDao();
		this.caseId = caseid;
		this.videoName = videoname;
		this.path = p;
		
		if(vd.notExist(videoName)) {
			this.videoId = vd.insertVideo(caseId, videoName, path);
			return videoId;
		} else {
			return -1;
		}
	}
	
}
