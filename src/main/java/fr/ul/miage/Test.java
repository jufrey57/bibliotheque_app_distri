package fr.ul.miage;

import java.sql.SQLException;

import fr.ul.miage.db.DbConnector;

public class Test {

	public static void main(String args[]) {
		DbConnector conn = new DbConnector();
		try {
			conn.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
