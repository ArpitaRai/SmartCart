package model;

import java.sql.*;

import dbConstants.DatabaseConstants;

public class DatabaseConnector {

private static Connection databaseConnection = null;
	
	private static Statement st; 

	private DatabaseConnector() {
	}
	
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
