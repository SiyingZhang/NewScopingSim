package edu.scopingsim.service;

import static spark.Spark.*;

import java.util.HashMap;
import spark.Session;
import com.google.gson.Gson;

import edu.scopingsim.bean.Case;


public class CaseService {
	
	private Gson gson = new Gson();
	private Case c = new Case();
	int id;
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
			String caseName = request.queryParams("caseName");
			String caseDescription = request.queryParams("caseDescription");
			System.out.println("caseName:"+ caseName);

			attributes.put("caseName", caseName);
			attributes.put("caseDescription", caseDescription);
			//System.out.println(attributes.get("caseName") + ": " + attributes.get("caseDescription"));
			
			try {
				int temp = c.addCase(caseName, caseDescription);  //Insert case			
				if(temp != -1) {
					id = temp;				
					System.out.println("id:" + id);
					
					//Add new case to session
					Session session = request.session(true);
					session.attribute("case", c);
					
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
		
	}
		
}
