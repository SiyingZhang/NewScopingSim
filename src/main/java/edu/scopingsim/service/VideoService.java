package edu.scopingsim.service;

import static spark.Spark.*;

import java.util.HashMap;
import spark.Session;

import com.google.gson.Gson;

import edu.scopingsim.bean.Case;
import edu.scopingsim.bean.Video;


public class VideoService {
	private Gson gson = new Gson();
	private Video v = new Video();
	int id = -1;
	
	/**
	 * Video service 
	 */
	public VideoService() {
		super();
		this.StartService();
	}
	
	private void StartService() {
		//create case process
		
		post("/uploadVideo", (request, response) -> {
			
			HashMap<String, Object> attributes = new HashMap<>();
			
			//Get video name and video description
			String videoName = request.queryParams("videoName");
			String videoPath = "/user/siying/Video/stock.mp4";
			System.out.println("Video Name: " + videoName);
			
			attributes.put("videoName", videoName);
			attributes.put("path", videoPath);
			
			try {
				Session session = request.session(true);
				Case case1 = session.attribute("case");
				
				//Add video into database
				int temp = v.addVideo(case1.getCaseId(), videoName, videoPath);
				if(temp != -1) {
					id = temp; 	//video Id
					session.attribute("video", v);  //Add video into session
					
					attributes.put("notExist", true);
					attributes.put("status", "Video uploaded successfully, redirecting now...");
				} else{
					attributes.put("notExist", false);
					attributes.put("status", "Video has been uploaded before");
				}				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				attributes.put("status", "Server Error, One more try, please.");
			}
			return gson.toJson(attributes);
		});
		
		/*
		get("/scopingsim/:caseName", (request, response) -> {
			
		}); */	
	}

}
