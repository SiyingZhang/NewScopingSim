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
		Statement statement = null;
		ResultSet rs = null;
		String query = "";

		public VideoDao() {
			connection = DatabaseConnector.getConnection();
			System.out.println("--------- CaseDao Connection --------");
		}
		
		/**
		 * Insert case record into TABLE case
		 * @param Case c
		 * @return case id
		 */
		public int insertVideo(Video v, int caseID) {
			
			query = "INSERT INTO scopingsim.case(caseName, caseDescription) values (?, ?)"; 
			//" + c.getCaseId() + "','" + c.getCaseName() + "','" + c.getCaseDescription() + "' )";
			try {
				query = "INSERT INTO scopingsim.video(path) values (?)"; 
				PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, v.getPath());
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

}
