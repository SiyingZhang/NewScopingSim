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

import edu.scopingsim.bean.Case;
import edu.scopingsim.bean.Event;
import edu.scopingsim.bean.Video;
import edu.scopingsim.utils.DatabaseConnector;

public class EventDao {
	private Connection connection;
	Statement statement = null;
	ResultSet rs = null;
	String query = "";

	public EventDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- EventDao Connection --------");
	}
	
	/**
	 * Insert event record into TABLE event
	 * @param Event e
	 * @return event id
	 */
	public int insertEvent(int videoId, String timeIndex, int x, int y) {
		
		query = "INSERT INTO scopingsim.event(videoId, timeIndex, x, y) values (?, ?, ?, ?)"; 
		//" + e.getVideoId() + "','" + e.getTimeIndex() + "','" + e.getX() + "' )";
		try {
			query = "INSERT INTO scopingsim.event(videoId, timeIndex, x, y) values (?, ?, ?, ?)"; 
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, videoId);
			ps.setString(2, timeIndex);
			ps.setInt(3, x);
			ps.setInt(4, y);
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
