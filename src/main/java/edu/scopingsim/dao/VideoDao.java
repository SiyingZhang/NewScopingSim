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

public class VideoDao {

		private Connection connection;
		String query = "";

		public VideoDao() {
			connection = DatabaseConnector.getConnection();
			System.out.println("--------- VideoDao Connection --------");
		}
		
		/**
		 * Insert video record into TABLE video
		 * @param Video v
		 * @return video id
		 */
		public int insertVideo(int caseId, String videoName, String path) {
			
			query = "INSERT INTO scopingsim.video(videoName, path, caseID) values (?, ?, ?)"; 
			//" + c.getCaseId() + "','" + c.getCaseName() + "','" + c.getCaseDescription() + "' )";
			try {
				query = "INSERT INTO scopingsim.video(videoName, path, caseID) values (?,?,?)"; 
				PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(1, caseId);
				ps.setString(2, videoName);
				ps.setString(3, path);
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
		 * Judge whether this video has already existed
		 * @param videoName
		 * @return
		 */
		public boolean notExist(String videoName) {
			
			boolean notExist = true;
			//Select all case with caseName
			query = "SELECT COUNT(*) AS VideoCount FROM scopingsim.video WHERE videoName=" + videoName;
			
			try {
				connection = DatabaseConnector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				
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
