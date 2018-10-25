package fr.ul.miage.entity;

import java.util.ArrayList;

public class Auteurs {
	private ArrayList<Auteur> Elements = new ArrayList();

	public Auteurs(ArrayList<Auteur> elements) {
		super();
		Elements = elements;
	}
	
	public Auteurs() {
		super();
	}

	public ArrayList<Auteur> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Auteur> elements) {
		Elements = elements;
	}
	
	public Auteur findOnName(String name) {
		for (Auteur elt : Elements) {
			if(elt.getNom().equals(name)) {
				return elt;
			}
		}
		return null;
	}
	
	public Auteur findOnID(String id) {
		for (Auteur elt : Elements) {
			if(elt.getID().equals(id)) {
				return elt;
			}
		}
		return null;
	}
}
