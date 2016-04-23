package edu.scopingsim.utils;

/**
 * @author siying
 * Connect to MySQL database scopingsim
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	
	//static reference to itself
	private static DatabaseConnector instance = new DatabaseConnector();
	
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/scopingsim";
	public static final String USER = "root";
	public static final String PASSWORD = "zhixian7913468";

	//private constructor
	public DatabaseConnector() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("ERROR: Unable to connect to Database!");
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
	

}
