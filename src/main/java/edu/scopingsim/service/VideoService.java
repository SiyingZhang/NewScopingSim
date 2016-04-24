package edu.scopingsim.service;

import static spark.Spark.*;


import java.util.HashMap;
import java.util.Random;
import java.util.UUID;


import com.google.gson.Gson;


import edu.scopingsim.bean.Case;
import edu.scopingsim.bean.Video;
import edu.scopingsim.dao.CaseDao;
import edu.scopingsim.dao.VideoDao;


public class VideoService {
	private Gson gson = new Gson();
	private VideoDao vd = new VideoDao();
	
	/**
	 * Case service 
	 */
	public VideoService() {
		super();
		this.StartService();
	}
	
	private void StartService() {
		//create case process
		
		post("/case", (request, response) -> {
			HashMap<String, Object> attributes = new HashMap<>();
			//Get case name and case description
			//String caseName = request.queryParams("caseName");
			//String caseDescription = request.queryParams("caseDescription");
			String path = "Kelly Clark";
			String videoName = null;
			int caseId = 0;
			attributes.put("path", path);
			
			try {
					Video v = new Video();

					v.setPath(path);
					vd.insertVideo(caseId, videoName, path);
					attributes.put("notExist", true);
					attributes.put("status", "Registration succeeded, Redirecting page ...");
				
					attributes.put("notExist", false);
					attributes.put("status", "Case has been created before, or this name has been useed before.");
				
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
