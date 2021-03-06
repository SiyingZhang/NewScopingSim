package edu.scopingsim.dao;
/**
 * Case - Data Access Object
 * @author siying 
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import edu.scopingsim.bean.Event;
import edu.scopingsim.bean.Note;
import edu.scopingsim.bean.Quiz;
import edu.scopingsim.utils.DatabaseConnector;

public class NoteDao {
	private Connection connection;
	Statement statement = null;
	ResultSet rs = null;
	String query = "";

	public NoteDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- CaseDao Connection --------");
	}
	
	/**
	 * Insert note record into TABLE note
	 * @param note n
	 * @return note id
	 */
	public int insertNote(int eventId, String noteText) {
		
		query = "INSERT INTO scopingsim.note(eventId, noteText) values (?, ?)"; 
		//" + e.getVideoId() + "','" + e.getTimeIndex() + "','" + e.getX() + "' )";
		try {
			query = "INSERT INTO scopingsim.note(eventId, noteText) values (?, ?)"; 
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, eventId);
			ps.setString(2, noteText);
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
	
	public ArrayList<Note> selectQuizByEventId(int eventid) {
		ArrayList<Note> noteList = new ArrayList<>();
		
		try {
			query = "SELECT * FROM scopingsim.note WHERE eventId=" + eventid;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				//Store as a quiz
				Note note = new Note();
				note.setNoteId(rs.getInt(1));
				note.setEventId(rs.getInt(2));
				note.setNoteText(rs.getString(3));
				noteList.add(note);
			}

		} catch (SQLException e4) {
			e4.printStackTrace();
		}
		return noteList;
	}
}
