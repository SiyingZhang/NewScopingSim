package edu.scopingsim.service;

import static spark.Spark.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import edu.scopingsim.bean.*;
import edu.scopingsim.dao.ChoiceDao;
import edu.scopingsim.dao.EventDao;
import edu.scopingsim.dao.NoteDao;
import edu.scopingsim.dao.QuizDao;
import freemarker.core.ReturnInstruction.Return;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class EventService {
	
	private Gson gson = new Gson();
	
	private int videoId = 1;
	private QuizDao qd = new QuizDao();
	private NoteDao nd = new NoteDao();
	private EventDao ed = new EventDao();
	private ChoiceDao choiceDao = new ChoiceDao();
	
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
		    
		    Event event = ed.selectByEventId(eventid);
		    ArrayList<Quiz> quizs = qd.selectQuizByEventId(eventid);
		    ArrayList<Note> notes =	nd.selectQuizByEventId(eventid);
		    
		    for(int i = 0; i < quizs.size(); i++) {
		    	ArrayList<Choice> choices = choiceDao.selectChoicesbyQuizId(quizs.get(i).getQuizId());
		    	quizs.get(i).setChoices(choices);
		    }
		    
		    
		    JsonObject outputObject = new JsonObject();
		    
		    // meta
		    JsonObject metaObject = new JsonObject();
		    metaObject.addProperty("x", event.getX());
		    metaObject.addProperty("y", event.getY());
		    metaObject.addProperty("time", event.getTimeIndex());
		    
		    outputObject.add("meta", metaObject);
		    
		    //notes
		    JsonArray notesArray = new JsonArray();
		    for(Note note : notes) {
		    	JsonObject noteObject = new JsonObject();
		    	noteObject.addProperty("text", note.getNoteText());
		    	notesArray.add(noteObject);
		    }
		    
		    outputObject.add("notes", notesArray);
		    
		    //quizs
		    JsonArray textQuizArray = new JsonArray();
		    JsonArray checkBoxArray = new JsonArray();
		    JsonArray multiplyChoiceArray = new JsonArray();
		    
		    for(Quiz quiz : quizs) {
//		    	JsonObject noteObject = new JsonObject();
//		    	noteObject.addProperty("text", note.getNoteText());
//		    	notesArray.add(noteObject);
		    	
		    	JsonObject quizObject = new JsonObject();
		    	quizObject.addProperty("question", quiz.getQuizText());
		    	
		    	if (quiz.getQuizType() == 0) {
		    		// text quiz
		    		ArrayList<Choice> choices = quiz.getChoices();
		    		String answer = choices.get(0).getChoiceText();
		    		quizObject.addProperty("answer", answer);
		    		textQuizArray.add(quizObject);
		    		
		    	} else if (quiz.getQuizType() == 1) {
		    		// checkbox quiz
		    		ArrayList<Choice> choices = quiz.getChoices();
		    		JsonArray choicesArray = new JsonArray();
		    		for (Choice choice : choices) {
		    			JsonObject choiceObject = new JsonObject();
		    			choiceObject.addProperty("text", choice.getChoiceText());
		    			choiceObject.addProperty("isCorrect", choice.getIsTrue());
		    			choicesArray.add(choiceObject);
		    		}
		    		quizObject.add("choices", choicesArray);
		    		checkBoxArray.add(quizObject);
		    		
		    	} else {
		    		// multiple choice quiz
		    		ArrayList<Choice> choices = quiz.getChoices();
		    		JsonArray choicesArray = new JsonArray();
		    		for (Choice choice : choices) {
		    			JsonObject choiceObject = new JsonObject();
		    			choiceObject.addProperty("text", choice.getChoiceText());
		    			choiceObject.addProperty("isCorrect", choice.getIsTrue());
		    			choicesArray.add(choiceObject);
		    		}
		    		quizObject.add("choices", choicesArray);
		    		multiplyChoiceArray.add(quizObject);
		    	}
		    	
		    }
		    
		    outputObject.add("textQuiz", textQuizArray);
		    outputObject.add("checkBoxQuiz", checkBoxArray);
		    outputObject.add("multipleChoiceQuiz", multiplyChoiceArray);
   
		    return outputObject;
		}, gson::toJson);
		
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
