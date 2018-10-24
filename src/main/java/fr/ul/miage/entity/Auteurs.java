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
	
	public ArrayList<Auteur> findOnName(String name) {
		ArrayList<Auteur> res = new ArrayList();
		for (Auteur elt : Elements) {
			if(elt.getNom().equals(name)) {
				res.add(elt);
			}
		}
		return res;
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
