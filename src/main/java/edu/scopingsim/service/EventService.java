package edu.scopingsim.service;

import static spark.Spark.*;


import java.util.HashMap;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.JsonObject;import com.google.gson.JsonParser;



public class EventService {
	
	private Gson gson = new Gson();

	public EventService() {
		super();
		this.StartService();
	}
	
	private void StartService() {

		//create an event
		post("/event", (request, response) -> {
			String eventJson = request.body();
			System.out.println(eventJson);
			
			//Convert json string to json object
			JsonObject jsonObject = new JsonParser().parse(eventJson).getAsJsonObject();
			System.out.println(jsonObject.toString());
			
			return jsonObject;
		});
		
	}

}


// class JsonUtil {
// 	public static Map<String, String> parse(String object) {
// 	return new Gson().fromJson(object, Map.class);
// }
