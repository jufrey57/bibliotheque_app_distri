package fr.ul.miage.entity;

import java.util.UUID;

public class Usager {
	
	private String ID = UUID.randomUUID().toString();
	private String Nom;
	private String Prenom;
	private int Age;
	private String mail;
	private boolean AbonnementValide = false;
	private int Retards = 0;
	
	public Usager(String nom, String prenom, int age, String mail) {
		super();
		Nom = nom;
		Prenom = prenom;
		Age = age;
		this.mail = mail;
	}
	
	public Usager(String iD, String nom, String prenom, int age, String mail, boolean abonnementValide, int retards) {
		super();
		ID = iD;
		Nom = nom;
		Prenom = prenom;
		Age = age;
		this.mail = mail;
		AbonnementValide = abonnementValide;
		Retards = retards;
	}

	public void addRetard() {
		Retards +=1 ;
	}
	
	public void clearRetards() {
		Retards = 0;
	}

	public String getID() {
		return ID;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isAbonnementValide() {
		return AbonnementValide;
	}

	public void setAbonnementValide(boolean abonnementValide) {
		AbonnementValide = abonnementValide;
	}

	public int getRetards() {
		return Retards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usager other = (Usager) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
}
