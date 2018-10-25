package fr.ul.miage.entity;

import java.util.UUID;

public class Auteur {

	private String ID = UUID.randomUUID().toString();
	private String Nom;
	private String Prenom;
	
	
	
	public Auteur(String nom, String prenom) {
		super();
		Nom = nom;
		Prenom = prenom;
	}
	
	public Auteur(String id, String nom, String prenom) {
		super();
		ID = id;
		Nom = nom;
		Prenom = prenom;
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
		Auteur other = (Auteur) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
	
}
