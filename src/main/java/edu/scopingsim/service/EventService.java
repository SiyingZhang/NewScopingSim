package edu.scopingsim.service;

import static spark.Spark.*;


import java.util.HashMap;
import java.util.Map;

import edu.scopingsim.bean.*;


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


	public int saveEvent(...) {

		Event eventBean = new Event();
		int id = addEvent(videoId, time, x, y);

		return id;
	}

	public void saveNotes(int eventId, ...) {
		Note noteBean = new Note();

		for () {

			addNote(eventId, noteText);

		}

	}

	public void saveTextQuiz(int eventId, ...) {
		// 0
		Quiz quizBean = new Quiz();
		for () {

			int quizId = addQuiz(eventId, 0, text);
			saveChoices(quizId, ...)

		}
		
	}

	public void saveCheckBoxQuiz(...) {
		// 1

		Quiz quizBean = new Quiz();
		for () {

			int quizId = addQuiz(eventId, 0, text);
			saveChoices(quizId, ...)

		}
		
	}

	public void saveMultipleChoiceQuiz(...) {
		// 2
		Quiz quizBean = new Quiz();
		for () {

			int quizId = addQuiz(eventId, 0, text);
			saveChoices(quizId, ...)

		}
	}

	public void saveChoices(int quizId, ...) {

		Choice choiceBean = new Choice();

		for () {

			addChoice(quizId, text, isCorrect);

		}		
	}

}


// class JsonUtil {
// 	public static Map<String, String> parse(String object) {
// 	return new Gson().fromJson(object, Map.class);
// }
