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
import edu.scopingsim.utils.DatabaseConnector;


public class CaseDao {
	private Connection connection;
	String query = "";

	public CaseDao() {
		connection = DatabaseConnector.getConnection();
		System.out.println("--------- CaseDao Connection --------");
	}
	
	/**
	 * Insert case record into TABLE case
	 * @param Case c
	 * @return case id
	 */
	public int insertCase(String casename, String casedescription) {
		
		try {
			query = "INSERT INTO scopingsim.case(caseName, caseDescription) values (?, ?)"; 
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, casename);
			ps.setString(2, casedescription);
			
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
	
	public Case selectCase(String caseName) {
		Case c = new Case();
		query = "SELECT * FROM scopingsim.case WHERE caseName=" + caseName;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			c.setCaseId(rs.getInt(1));
			c.setCaseName(rs.getString(2));
			c.setCaseDescription(rs.getString(3));
								
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Select case by case ID
	 * @param caseId int
	 * @return selected Case
	 */

	public Case selectCaseById(int caseId) {
		Case c = new Case();
		query = "SELECT * FROM scopingsim.case WHERE caseId=" + caseId; 
		
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			String caseName = rs.getString(2);
			String caseDescription = rs.getString(3);

			c.setCaseId(rs.getInt(1));
			c.setCaseName(rs.getString(2));
			c.setCaseDescription(rs.getString(3));

			System.out.println("---retrieved user info: " + caseName + ", " + caseDescription);
			return c;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
 
	/**
	 * Judge whether this caseName has been registered
	 * @param caseName
	 * @return true/false
	 */
	public boolean notExist(String caseName) {
		
		boolean notExist = true;
		int count = 0;
		//Select all case with caseName
		query = "SELECT COUNT(*) AS CaseCount FROM scopingsim.case WHERE caseName =" + caseName;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				count = rs.getInt("CaseCount");
			}
			notExist = (count == 0) ? true:false;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return notExist;
	}
}
