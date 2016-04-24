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
			int caseId = Integer.parseInt(request.queryParams("caseId"));
			int videoId = 0;
			
			attributes.put("videoName", videoName);
			attributes.put("path", videoPath);
			
			try {
					Video v = new Video();
					int temp = v.addVideo(caseId, videoName, videoPath);
					if(temp != -1) {
						videoId = temp;
						attributes.put("notExist", true);
						attributes.put("status", "Video uploaded successfully, redirecting now...");
					} else{
						videoId = -1;
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
