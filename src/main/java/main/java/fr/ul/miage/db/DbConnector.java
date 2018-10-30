package main.java.fr.ul.miage.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	public static void connect() throws SQLException{
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
	public static Auteur getAuteur(String id) {
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
	public static Emprunt getEmprunt(String id) {
		Emprunt res = new Emprunt(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM emprunt WHERE ID_emprunt='"+id+"'";
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
	public static Exemplaire getExemplaire(String id) {
		Exemplaire res = new Exemplaire(null,null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM exemplaire WHERE ID_exemplaire='"+id+"'";
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
	
	//done
	public static Oeuvre getOeuvre(String id) {
		Oeuvre res = new Oeuvre(null,new Auteur(null,null));
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM oeuvre WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                ArrayList<Auteur> auteurs = new ArrayList();
	                auteurs.add(getAuteur(rs.getString(3)));
	                res = new Oeuvre(rs.getString(1),rs.getString(2),auteurs,Integer.parseInt(rs.getString(4)));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	//done
	public static Reservation getReservation(String id) {
		Reservation res = new Reservation(null,null,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM reservation WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Reservation(rs.getString(1),Date.valueOf(rs.getString(2)),getUsager(rs.getString(4)),getOeuvre(rs.getString(3)),Boolean.parseBoolean(rs.getString(5)));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	//done
	public static Usager getUsager(String id) {
		Usager res = new Usager(null,null,0,null);
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM usager WHERE ID_auteur='"+id+"'";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	                rs.first();
	                res = new Usager(rs.getString(1),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5),Boolean.valueOf(rs.getString(6)),Integer.parseInt(rs.getString(7)));
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
		return res;
	}
	
	//done
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
	
	//done
	public void loadEmprunts() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM emprunts";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	            	while(rs.next()) {
	            		emprunts.getElements().add(new Emprunt(rs.getString(1),rs.getDate(2),rs.getDate(3),getUsager(rs.getString(3)),getExemplaire(rs.getString(5))));
	            	}
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public void loadExemplaires() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM exemplaire";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	            	while(rs.next()) {
	            		exemplaires.getElements().add(new Exemplaire(rs.getString(1),getOeuvre(rs.getString(4)),rs.getString(2),rs.getDate(3)));
	            	}
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public void loadOeuvres() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
			ArrayList<Auteur> auteurs;
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM oeuvre";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	            	while(rs.next()) {
	            		auteurs = new ArrayList();
	            		auteurs.add(getAuteur(rs.getString(3)));
	            		oeuvres.getElements().add(new Oeuvre(rs.getString(1),rs.getString(2),auteurs,Integer.parseInt(rs.getString(4))));
	            	}
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public void loadReservations() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM reservation";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	            	while(rs.next()) {
	            		reservations.getElements().add(new Reservation(rs.getString(1),Date.valueOf(rs.getString(2)),getUsager(rs.getString(4)),getOeuvre(rs.getString(3)),Boolean.parseBoolean(rs.getString(5))));
	            	}
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public void loadUsagers() {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
			// create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "SELECT * FROM usager";
	        	//System.out.println(str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                //position result to first
	            	while(rs.next()) {
	            		usagers.getElements().add(new Usager(rs.getString(1),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5),Boolean.valueOf(rs.getString(6)),Integer.parseInt(rs.getString(7))));
	            	}
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void loadAll() {
		loadAuteurs();
		loadEmprunts();
		loadExemplaires();
		loadOeuvres();
		loadReservations();
		loadUsagers();
	}
	
	//done
	public static void putAuteur(Auteur auteur) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO auteur (ID_auteur, Nom, Prenom) VALUES ('"
	        			+auteur.getID()+"','"
	        			+auteur.getNom()+"','"
	        			+auteur.getPrenom()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void putEmprunt(Emprunt emprunt) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO emprunt (ID_emprunt, Date_debut, Date_fin, ID_Usager, ID_exemplaire) VALUES ('"
	        			+emprunt.getID()+"','"
	        			+emprunt.getDateDebut().toString()+"','"
	        			+emprunt.getDateRetour().toString()+"','"
	        			+emprunt.getUsager().getID()+"','"
	        			+emprunt.getExeplaire().getID()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void putExemplaire(Exemplaire exemplaire) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO exemplaire (ID_exemplaire, Vices, Date_acquisition, ID_oeuvre) VALUES ('"
	        			+exemplaire.getID()+"','"
	        			+exemplaire.getVices()+"','"
	        			+exemplaire.getDateAcquisition().toString()+"','"
	        			+exemplaire.getOeuvre().getID()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void putOeuvre(Oeuvre oeuvre) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO oeuvre (ID_oeuvre, Nom, Auteur, NbrResa) VALUES ('"
	        			+oeuvre.getID()+"','"
	        			+oeuvre.getNom()+"','"
	        			+oeuvre.getAuteurs().get(0)+"','"
	        			+oeuvre.getNbrResa()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void putReservation(Reservation reservation) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO reservation (ID_reservation, DateDemande, ID_Oeuvre, ID_Usager, Active) VALUES ('"
	        			+reservation.getID()+"','"
	        			+reservation.getDateDemande().toString()+"','"
	        			+reservation.getOeuvre().getID()+"','"
	        			+reservation.getUsager().getID()+"','"
	        			+reservation.isActive()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void putUsager(Usager usager) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "INSERT INTO reservation (ID_usager, Nom, Prenom, Age, Mail, Abonnement_valide, retards) VALUES ('"
	        			+usager.getID()+"','"
	        			+usager.getNom()+"','"
	        			+usager.getPrenom()+"','"
	        			+usager.getAge()+"','"
	        			+usager.getMail()+"','"
	        			+usager.isAbonnementValide()+"','"
	        			+usager.getRetards()+"')";
	        	LOGGER.log(Level.FINEST,str);
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Insertion bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void delAuteur(String id) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "DELETE FROM auteur where ID_auteur='"
	        			+id+"'";
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Suppression bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void delEmprunt(String id) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "DELETE FROM emprunt where ID_emprunt='"
	        			+id+"'";
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Suppression bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void delExemplaire(String id) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "DELETE FROM exemplaire where ID_exemplaire='"
	        			+id+"'";
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Suppression bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void delOeuvre(String id) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "DELETE FROM oeuvre where ID_oeuvre='"
	        			+id+"'";
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Suppression bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void delReservation(String id) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "DELETE FROM reservation where ID_reservation='"
	        			+id+"'";
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Suppression bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	//done
	public static void delUsager(String id) {
		try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bibliothequedb","root","");) {
	        // create a Statement
	        try (Statement stmt = conn.createStatement()) {
	            //execute query
	        	String str = "DELETE FROM usager where ID_usager='"
	        			+id+"'";
	            try (ResultSet rs = stmt.executeQuery(str)) {
	                LOGGER.log(Level.FINEST,"Suppression bien effectuée"); 
	            }
	        }
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
}
