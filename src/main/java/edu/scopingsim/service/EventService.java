package edu.scopingsim.service;

import static spark.Spark.*;


import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class EventService {

	public EventService() {
		super();
		this.StartService();
	}
	
	private void StartService() {

		//create an event
		post("/event", (request, response) -> {
			String eventJson = request.body();
			System.out.println(eventJson);
		});
		
	}

}


// class JsonUtil {
// 	public static Map<String, String> parse(String object) {
// 	return new Gson().fromJson(object, Map.class);
// }
