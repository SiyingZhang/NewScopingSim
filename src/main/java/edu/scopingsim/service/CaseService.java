package edu.scopingsim.service;

import static spark.Spark.*;

import java.util.HashMap;

import com.google.gson.Gson;

import edu.scopingsim.bean.Case;
import edu.scopingsim.dao.CaseDao;

public class CaseService {
	
	private Gson gson = new Gson();
	private CaseDao cd = new CaseDao();
	
	/**
	 * Case service 
	 */
	public CaseService() {
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
			String caseName = "Kelly Clark";
			String caseDescription = "Stomachache";
			attributes.put("caseName", caseName);
			attributes.put("caseDescription", caseDescription);
			
			try {
				if(this.checkNotExist(caseName)) {
					Case c = new Case();

					c.setCaseName(caseName);
					c.setCaseDescription(caseDescription);
					cd.insertCase(c);
					attributes.put("notExist", true);
					attributes.put("status", "Registration succeeded, Redirecting page ...");
				} else {
					attributes.put("notExist", false);
					attributes.put("status", "Case has been created before, or this name has been useed before.");
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
	
	//Check whether the case name exist
	private boolean checkNotExist(String caseName) {
		boolean notExist = true;
		try {
			notExist = cd.notExist(caseName);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(this.getClass() + ".checkExistence()");
			e.printStackTrace();
		}
		return notExist;
	}
	
}
