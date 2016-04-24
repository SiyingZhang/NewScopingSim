package edu.scopingsim.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import edu.scopingsim.bean.Event;
import edu.scopingsim.bean.Note;
import edu.scopingsim.bean.Quiz;
import edu.scopingsim.utils.DatabaseConnector;

public class QuizDao {
	private Connection connection;
	Statement statement = null;
	ResultSet rs = null;
	String query = "";

	public QuizDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- CaseDao Connection --------");
	}
	
	/**
	 * Insert quiz record into TABLE quiz
	 * @param quiz q
	 * @return quiz id
	 */
	public int insertNote(int event, int quizType, String quizText) {
		
		query = "INSERT INTO scopingsim.quiz(event, quizText, quizType) values (?, ?, ?)"; 
		//" + e.getVideoId() + "','" + e.getTimeIndex() + "','" + e.getX() + "' )";
		try {
			query = "INSERT INTO scopingsim.quiz(event, quizText,quizType) values (?, ?, ?)"; 
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
}
