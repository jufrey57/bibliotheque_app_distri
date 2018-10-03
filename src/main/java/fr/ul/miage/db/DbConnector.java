package main.java.fr.ul.miage.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {
	
	public void connect() throws SQLException{
		//create connection for a server installed in localhost, with a user "root" with no password
	    try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bibliothequedb?user=root&password=");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	            try (ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'")) {
	                //position result to first
	                rs.first();
	                System.out.println(rs.getString(1)); //result is "Hello World!"
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
}
