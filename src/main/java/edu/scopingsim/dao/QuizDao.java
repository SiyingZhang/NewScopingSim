package edu.scopingsim.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import edu.scopingsim.bean.Quiz;
import edu.scopingsim.utils.DatabaseConnector;

public class QuizDao {
	private Connection connection;
	Statement statement = null;
	String query = "";

	public QuizDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- QuizDao Connection --------");
	}
	
	/**
	 * Insert quiz record into TABLE quiz
	 * @param quiz q
	 * @return quiz id
	 */
	public int insertQuiz(int event, int quizType, String quizText) {
		
		try {
			query = "INSERT INTO scopingsim.quiz(event,quizType, quizText) values (?, ?, ?)"; 
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, event);
			ps.setInt(2, quizType);
			ps.setString(3, quizText);
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
	
	public ArrayList<Quiz> selectQuizByEventId(int eventid) {
		ArrayList<Quiz> quizList = new ArrayList<>();
		
		try {
			query = "SELECT * FROM scopingsim.quiz WHERE event=" + eventid;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()) {
				//Store as a quiz
				Quiz quiz = new Quiz();
				quiz.setQuizId(rs.getInt(1));
				quiz.setQuizType(rs.getInt(2));
				quiz.setQuizText(rs.getString(3));
				quizList.add(quiz);
			}
		} catch (SQLException e4) {
			e4.printStackTrace();
		}
		return quizList;
	}
}
