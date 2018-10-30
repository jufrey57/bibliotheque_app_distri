package fr.ul.miage.control;

import java.util.ArrayList;

import fr.ul.miage.entity.Auteur;
import fr.ul.miage.entity.Auteurs;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Exemplaires;
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Oeuvres;
import main.java.fr.ul.miage.db.DbConnector;

public class GestionOeuvresExemplaires {

	private Oeuvres oeuvres;
	private Exemplaires exemplaires;
	private Auteurs auteurs;
	
	public Oeuvre creerOeuvre(String nom, Auteur auteur) {
		Oeuvre o = new Oeuvre(nom,auteur);
		oeuvres.getElements().add(o);
		DbConnector.putOeuvre(o);
		return o;
	}
	
	public Oeuvre trouverOeuvre(String nom) {
		return oeuvres.findOnTitle(nom);
	}
	
	public void supprimerOeuvre(Oeuvre oeuvre) {
		if(oeuvres.getElements().contains(oeuvre)) {
			oeuvres.getElements().remove(oeuvre);
			DbConnector.delOeuvre(oeuvre.getID());
		}
	}
	
	public Auteur creerAuteur(String nom, String prenom) {
		Auteur a = new Auteur(nom,prenom);
		auteurs.getElements().add(a);
		DbConnector.putAuteur(a);
		return a;
	}
	
	public void supprimerAuteur(Auteur auteur) {
		if(auteurs.getElements().contains(auteur)) {
			auteurs.getElements().remove(auteur);
			DbConnector.delAuteur(auteur.getID());
		}
	}
	
	public Auteur trouverAuteur(String nom) {
		return auteurs.findOnName(nom);
	}
	
	public Exemplaire creerExemplaire(Oeuvre o) {
		Exemplaire e = new Exemplaire(o);
		exemplaires.getElements().add(e);
		DbConnector.putExemplaire(e);
		return e;
	}
	
	public ArrayList<Exemplaire> trouverExemplaire(Oeuvre o) {
		return exemplaires.findOnOeuvre(o);
	}
	
	public void supprimerExemplaire(Exemplaire exemplaire) {
		if(exemplaires.getElements().contains(exemplaire)) {
			exemplaires.getElements().remove(exemplaire);
			DbConnector.delExemplaire(exemplaire.getID());
		}
	}
}
