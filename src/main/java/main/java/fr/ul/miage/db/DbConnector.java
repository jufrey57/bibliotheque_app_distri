package main.java.fr.ul.miage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.ul.miage.control.GestionResEmprunt;
import fr.ul.miage.entity.Auteur;
import fr.ul.miage.entity.Auteurs;
import fr.ul.miage.entity.Emprunt;
import fr.ul.miage.entity.Emprunts;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Exemplaires;
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Oeuvres;
import fr.ul.miage.entity.Reservation;
import fr.ul.miage.entity.Reservations;
import fr.ul.miage.entity.Usager;
import fr.ul.miage.entity.Usagers;

public class DbConnector {
	
	private Auteurs auteurs;
	private Emprunts emprunts;
	private Exemplaires exemplaires;
	private Oeuvres oeuvres;
	private Reservations reservations;
	private Usagers usagers;
	
	
	
	public DbConnector(Auteurs auteurs, Emprunts emprunts, Exemplaires exemplaires, Oeuvres oeuvres,
			Reservations reservations, Usagers usagers) {
		super();
		this.auteurs = auteurs;
		this.emprunts = emprunts;
		this.exemplaires = exemplaires;
		this.oeuvres = oeuvres;
		this.reservations = reservations;
		this.usagers = usagers;
	}
	
	

	public DbConnector() {
		super();
		this.auteurs = new Auteurs();
		this.emprunts = new Emprunts();
		this.exemplaires = new Exemplaires();
		this.oeuvres = new Oeuvres();
		this.reservations = new Reservations();
		this.usagers = new Usagers();
	}



	private static final Logger LOGGER = Logger.getLogger(DbConnector.class.getName());
	
	public void connect() throws SQLException{
		//create connection for a server installed in localhost, with a user "root" with no password
	    try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	            try (ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'")) {
	                //position result to first
	                rs.first();
	                LOGGER.log(Level.FINEST,rs.getString(1)); //result is "Hello World!"
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public Auteur getAuteur(String id) {
		Auteur res = new Auteur(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Auteur(rs.getString(1),rs.getString(2),rs.getString(3));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	//done
	public Emprunt getEmprunt(String id) {
		Emprunt res = new Emprunt(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur WHERE ID_emprunt='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Emprunt(rs.getString(1),rs.getDate(2),rs.getDate(3),getUsager(rs.getString(3)),getExemplaire(rs.getString(5)));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	//done
	public Exemplaire getExemplaire(String id) {
		Exemplaire res = new Exemplaire(null,null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur WHERE ID_exemplaire='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Exemplaire(rs.getString(1),getOeuvre(rs.getString(4)),rs.getString(2),rs.getDate(3));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	
	public Oeuvre getOeuvre(String id) {
		Auteur res = new Auteur(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Auteur(rs.getString(1),rs.getString(2),rs.getString(3));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	public Reservation getReservation(String id) {
		Auteur res = new Auteur(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Auteur(rs.getString(1),rs.getString(2),rs.getString(3));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	public Usager getUsager(String id) {
		Auteur res = new Auteur(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Auteur(rs.getString(1),rs.getString(2),rs.getString(3));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	public void loadAuteurs() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM auteur";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	            	while(rs.next()) {
	            		auteurs.getElements().add(new Auteur(rs.getString(1),rs.getString(2),rs.getString(3)));
	            	}
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void enterAuteur(Auteur auteur) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO auteur (ID_auteur, Nom, Prenom) VALUES ('"+auteur.getID()+"','"+auteur.getNom()+"','"+auteur.getPrenom()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
}
