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
import edu.scopingsim.bean.Video;
import edu.scopingsim.utils.DatabaseConnector;

public class EventDao {
	private Connection connection;
	Statement statement = null;
	ResultSet rs = null;
	String query = "";

	public EventDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- CaseDao Connection --------");
	}
	
	/**
	 * Insert event record into TABLE event
	 * @param Event e
	 * @return event id
	 */
	public int insertVideo(Video v, int caseID) {
		
		query = "INSERT INTO scopingsim.event(videoName, path) values (?, ?)"; 
		//" + c.getCaseId() + "','" + c.getCaseName() + "','" + c.getCaseDescription() + "' )";
		try {
			query = "INSERT INTO scopingsim.video(videoName, path) values (?,?)"; 
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, v.getPath());
			ps.setString(2, v.getVideoName());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				int autoKey = rs.getInt(1);
				return autoKey;
			} else {
				return -1;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Select case by case Name
	 * @param caseName String
	 * @return selected Case
	 */
	public boolean notExist(String videoName) {
		
		boolean notExist = true;
		//Select all case with caseName
		query = "SELECT COUNT(*) AS VideoCount FROM video WHERE videoName = '" + videoName +"'";
		
		try {
			connection = DatabaseConnector.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			int count = rs.getInt("VideoCount");
			notExist = (count == 0) ? true:false;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println(this.getClass() + ".notExist: can't close the connection.");
				}
			}
		}
		return notExist;
	}

}
