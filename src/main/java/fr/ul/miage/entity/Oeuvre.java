package fr.ul.miage.entity;

import java.util.ArrayList;
import java.util.UUID;

public class Oeuvre {
	
	private String ID = UUID.randomUUID().toString();
	private String Nom;
	private ArrayList<Auteur> Auteurs = new ArrayList();
	private int nbrResa = 0;
	
	public Oeuvre(String nom, ArrayList<Auteur> auteurs) {
		super();
		Nom = nom;
		Auteurs = auteurs;
	}
	
	public Oeuvre(String nom, Auteur auteur) {
		super();
		ID = UUID.randomUUID().toString();
		Nom = nom;
		Auteurs.add(auteur);
	}

	public Oeuvre(String iD, String nom, ArrayList<Auteur> auteurs, int nbrResa) {
		super();
		ID = iD;
		Nom = nom;
		Auteurs = auteurs;
		this.nbrResa = nbrResa;
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

	public ArrayList<Auteur> getAuteurs() {
		return Auteurs;
	}

	public void setAuteurs(ArrayList<Auteur> auteurs) {
		Auteurs = auteurs;
	}

	public int getNbrResa() {
		return nbrResa;
	}

	public void addResa() {
		this.nbrResa += 1;
	}
	
	public void subResa() {
		this.nbrResa -= 1;
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
		Oeuvre other = (Oeuvre) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
}
