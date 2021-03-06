package edu.scopingsim.service;

import java.util.HashMap;

import spark.template.freemarker.*;
import spark.ModelAndView;
import static spark.Spark.*;



public class HostService {
	public static void main(String[] args) {
		port(5000);
		staticFileLocation("/public");
		
		//get("/hello", (request, response) -> "Hello World!");
		get("/", (request, response) -> {
			HashMap<String, Object> attributes = new HashMap<>();
			
			attributes.put("title", "ScopingSim-Create Case");
			return new ModelAndView(attributes, "createCase.ftl");
		}, new FreeMarkerEngine());
		
		CaseService cs = new CaseService();
		//VideoService vs = new VideoService();
		EventService es = new EventService();
		
	}
}
