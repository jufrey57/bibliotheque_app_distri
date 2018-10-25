package fr.ul.miage.entity;

import java.util.ArrayList;

public class Magazine extends Oeuvre {
	
private int TempsPret = 10;
	
	public Magazine(String nom, ArrayList<Auteur> auteurs) {
		super(nom, auteurs);
		// TODO Auto-generated constructor stub
	}
	
	public Magazine(String nom, Auteur auteur) {
		super(nom, auteur);
		// TODO Auto-generated constructor stub
	}

	public int getTempsPret() {
		return TempsPret;
	}

	public void setTempsPret(int tempsPret) {
		TempsPret = tempsPret;
	}
}
