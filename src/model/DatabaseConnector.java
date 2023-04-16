package model;

import java.sql.*;

import dbConstants.DatabaseConstants;

public class DatabaseConnector {

	private static Connection dbConnection = null;
	
	private static Statement st; 

	private DatabaseConnector() {
	}
	
	public static Connection getInstance() throws Exception {
		if (dbConnection == null) {
			synchronized (DatabaseConnector.class) {
				if (dbConnection == null) {
					Class.forName("com.mysql.jdbc.Driver");
					dbConnection = DriverManager.getConnection(DatabaseConstants.url,
							DatabaseConstants.username, DatabaseConstants.password);
				}
			}
		}
		return dbConnection;
	}
	
	public static ResultSet getItemsFromCatalog(String catalogId) {
		try {
			Connection conn = DatabaseConnector.getInstance();
			st = conn.createStatement();
			String query = "select productId, productName, productPrice, productQuantity, catalog from product_list where catalog = '"
					+ catalogId + "'";
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// closeStatement is a common method to close the statement
	public static void closeStatement() {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
