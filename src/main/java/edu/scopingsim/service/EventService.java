package edu.scopingsim.service;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.scopingsim.bean.*;
import edu.scopingsim.dao.QuizDao;
import freemarker.core.ReturnInstruction.Return;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;import com.google.gson.JsonParser;



public class EventService {
	
	private Gson gson = new Gson();
	
	private int videoId = 1;
	private QuizDao qd = new QuizDao();
	
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
			
			JsonObject meta = jsonObject.getAsJsonObject("meta");
			// save event
			int eventId = saveEvent(meta);
			
			JsonArray notesArray = jsonObject.getAsJsonArray("notes");
			if (notesArray.size() > 0) {
				saveNotes(eventId, notesArray);
			}
			
			JsonArray textQuizArray = jsonObject.getAsJsonArray("textQuiz");
			if (textQuizArray.size() > 0) {
				saveTextQuiz(eventId, textQuizArray);
			}
			
			JsonArray checkBoxQuizArray = jsonObject.getAsJsonArray("checkBoxQuiz");
			if (checkBoxQuizArray.size() > 0) {
				saveCheckBoxQuiz(eventId, checkBoxQuizArray);
			}

			JsonArray multipleChoiceQuizArray = jsonObject.getAsJsonArray("multipleChoiceQuiz");
			if (multipleChoiceQuizArray.size() > 0) {
				saveMultipleChoiceQuiz(eventId, multipleChoiceQuizArray);
			}
			
			Map<String, String> resMap = new HashMap<>();
			resMap.put("eventId", eventId + "");
			String time = meta.get("time").getAsString();
			resMap.put("timestamp", time);
			
			return resMap;
		}, gson::toJson);
		
		
		get("/event/:id", (request, response) -> {
		    String id = request.params(":id");
		    int eventid = Integer.parseInt(id);
		    
		    ArrayList<Quiz> quizs= qd.selectQuizByEventId(eventid);
		    
		    return null;
		});
		
	}


	public int saveEvent(JsonObject meta) {
		Event eventBean = new Event();
		
		String time = meta.get("time").getAsString();
		int x = meta.get("x").getAsInt();
		int y = meta.get("y").getAsInt();
		int id = eventBean.addEvent(videoId, time, x, y);

		return id;
	}

	public void saveNotes(int eventId, JsonArray notesArray) {
		Note noteBean = new Note();
		for (int i=0; i<notesArray.size(); i++) {
			JsonObject noteObj = notesArray.get(i).getAsJsonObject();
			String noteText = noteObj.get("text").getAsString();
			noteBean.addNote(eventId, noteText);
		}

	}

	public void saveTextQuiz(int eventId, JsonArray textQuizArray) {
		// 0
		Quiz quizBean = new Quiz();
		for (int i=0; i<textQuizArray.size(); i++) {
			
			JsonObject quizObj = textQuizArray.get(i).getAsJsonObject();
			String question = quizObj.get("question").getAsString();
			int quizId = quizBean.addQuiz(eventId, 0, question);
			String answer = quizObj.get("answer").getAsString();
			saveChoices(quizId, answer);

		}
		
	}

	public void saveCheckBoxQuiz(int eventId, JsonArray checkBoxQuizArray) {
		// 1

		Quiz quizBean = new Quiz();
		for (int i=0; i<checkBoxQuizArray.size(); i++) {
			
			JsonObject quizObj = checkBoxQuizArray.get(i).getAsJsonObject();
			String question = quizObj.get("question").getAsString();
			int quizId = quizBean.addQuiz(eventId, 1, question);
			JsonArray choicesArray = quizObj.get("choices").getAsJsonArray();
			saveChoices(quizId, choicesArray);

		}
		
	}
	
	// could be integrated into saveCheckBoxQuiz
	public void saveMultipleChoiceQuiz(int eventId, JsonArray multipleChoiceArray) {
		// 2
		Quiz quizBean = new Quiz();
		for (int i=0; i<multipleChoiceArray.size(); i++) {
			
			JsonObject quizObj = multipleChoiceArray.get(i).getAsJsonObject();
			String question = quizObj.get("question").getAsString();
			int quizId = quizBean.addQuiz(eventId, 2, question);
			JsonArray choicesArray = quizObj.get("choices").getAsJsonArray();
			saveChoices(quizId, choicesArray);

		}
	}

	public void saveChoices(int quizId, JsonArray choicesArray) {

		Choice choiceBean = new Choice();

		for (int i=0; i<choicesArray.size(); i++) {
			
			JsonObject choiceObj = choicesArray.get(i).getAsJsonObject();
			String text = choiceObj.get("text").getAsString();
			boolean isCorrect = choiceObj.get("isCorrect").getAsBoolean();
			choiceBean.addChoice(quizId, text, isCorrect);

		}		
	}
	
	// for text quiz
	public void saveChoices(int quizId, String answer) {

		Choice choiceBean = new Choice();
		choiceBean.addChoice(quizId, answer, true); // always true
		
	}
	
}


// class JsonUtil {
// 	public static Map<String, String> parse(String object) {
// 	return new Gson().fromJson(object, Map.class);
// }
