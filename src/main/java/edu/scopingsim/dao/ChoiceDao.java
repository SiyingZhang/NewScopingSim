package edu.scopingsim.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import edu.scopingsim.bean.Choice;
import edu.scopingsim.bean.Event;
import edu.scopingsim.bean.Note;
import edu.scopingsim.bean.Quiz;
import edu.scopingsim.utils.DatabaseConnector;

public class ChoiceDao {
	private Connection connection;
	Statement statement = null;
	ResultSet rs = null;
	String query = "";

	public ChoiceDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- CaseDao Connection --------");
	}
	
	/**
	 * Insert choice record into TABLE choice
	 * @param choice c
	 * @return choice c
	 */
	public int insertChoice(int quizId, String choiceText, boolean isTrue) {
		
		query = "INSERT INTO scopingsim.choice(quizId, choiceText, isTrue) values (?, ?, ?)"; 
		//" + e.getVideoId() + "','" + e.getTimeIndex() + "','" + e.getX() + "' )";
		try {
			query = "INSERT INTO scopingsim.choice(quizId, choiceText,isTrue) values (?, ?, ?)"; 
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, quizId);
			ps.setString(2, choiceText);
			ps.setBoolean(3, isTrue);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				int autoKey = rs.getInt(1);
				return autoKey;
			} else {
				return -1;
			}
			
		} catch (SQLException e3) {
			// TODO: handle exception
			e3.printStackTrace();
			return -1;
		}
	}
	
	/*
	public HashMap<Quiz, ArrayList<Choice>> selectQuizChoices(int eventid) {
		HashMap<Quiz, ArrayList<Choice>> quizChoiceMap = new HashMap<>();
		Quiz quiz = new Quiz();
		Choice choice = new Choice();
		ArrayList<Choice> choices = new ArrayList<>();
		
		try {
			
			
		} catch (SQLException e4) {
			// TODO: handle exception
			e4.printStackTrace();
		}
		return quizChoiceMap;
	}*/
	
	public ArrayList<Choice> selectChoicesbyQuizId(int quizid) {
		ArrayList<Choice> choiceList = new ArrayList<>();
		
		try {
			query = "SELECT * FROM scopingsim.choice WHERE quizId=" + quizid;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				Choice choice = new Choice();
				choice.setChoiceId(rs.getInt(1));
				choice.setQuizId(quizid);
				choice.setChoiceText(rs.getString(3));
				choice.setTrue(rs.getInt(4));
				
				choiceList.add(choice);
			}
			
		} catch (SQLException e4) {
			// TODO: handle exception
			e4.printStackTrace();
		}
		return choiceList;
	}

}
