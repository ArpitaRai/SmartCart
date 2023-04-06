package model;

import java.sql.*;

import dbConstants.DatabaseConstants;

public class DatabaseConnector {

private static Connection databaseConnection = null;
	
	private static Statement st; 

	private DatabaseConnector() {
	}
	
	// Singleton class instance to avoid multiple Database connections
	// Returns Database connection instance
	public static Connection getInstance() throws Exception {
		if (databaseConnection == null) {
			synchronized (DatabaseConnector.class) {
				if (databaseConnection == null) {
					Class.forName("com.mysql.cj.jdbc.Driver");
					databaseConnection = DriverManager.getConnection(DatabaseConstants.url,
							DatabaseConstants.username, DatabaseConstants.password);
				}
			}
		}
		return databaseConnection;
	}
}
