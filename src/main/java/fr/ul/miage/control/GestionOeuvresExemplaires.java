package fr.ul.miage.control;

import java.util.ArrayList;

import fr.ul.miage.entity.Auteur;
import fr.ul.miage.entity.Auteurs;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Exemplaires;
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Oeuvres;

public class GestionOeuvresExemplaires {

	private Oeuvres oeuvres;
	private Exemplaires exemplaires;
	private Auteurs auteurs;
	
	public Oeuvre creerOeuvre(String nom, Auteur auteur) {
		Oeuvre o = new Oeuvre(nom,auteur);
		oeuvres.getElements().add(o);
		return o;
	}
	
	public ArrayList<Oeuvre> trouverOeuvre(String nom) {
		return oeuvres.findOnTitle(nom);
	}
	
	public void supprimerOeuvre(Oeuvre oeuvre) {
		oeuvres.getElements().remove(oeuvres.findOnID(oeuvre.getID()));
	}
	
	public Auteur creerAuteur(String nom, String prenom) {
		Auteur a = new Auteur(nom,prenom);
		auteurs.getElements().add(a);
		return a;
	}
	
	public void supprimerAuteur(Auteur auteur) {
		if(auteurs.getElements().contains(auteur)) {
			auteurs.getElements().remove(auteur);
		}
	}
	
	public ArrayList<Auteur> trouverAuteur(String nom) {
		return auteurs.findOnName(nom);
	}
	
	public Exemplaire creerExemplaire(Oeuvre o) {
		Exemplaire e = new Exemplaire(o);
		exemplaires.getElements().add(e);
		return e;
	}
	
	public ArrayList<Exemplaire> trouverExemplaire(Oeuvre o) {
		return exemplaires.findOnOeuvre(o);
	}
	
	public void supprimerExemplaire(Exemplaire exemplaire) {
		exemplaires.getElements().remove(exemplaires.findOnID(exemplaire.getID()));
	}
}
