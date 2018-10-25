package fr.ul.miage.entity;

import java.util.ArrayList;

public class Exemplaires {
	
	private ArrayList<Exemplaire> Elements = new ArrayList();

	public Exemplaires(ArrayList<Exemplaire> elements) {
		super();
		Elements = elements;
	}
	
	public Exemplaires() {
		super();
	}

	public ArrayList<Exemplaire> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Exemplaire> elements) {
		Elements = elements;
	}
	
	
	public Exemplaire findOnID(String id) {
		for (Exemplaire elt : Elements) {
			if(elt.getID().equals(id)) {
				return elt;
			}
		}
		return null;
	}
	
	public ArrayList<Exemplaire> findOnOeuvre(Oeuvre o) {
		ArrayList<Exemplaire> res = new ArrayList();
		for (Exemplaire elt : Elements) {
			if(elt.getOeuvre().equals(o)) {
				res.add(elt);
			}
		}
		return res;
	}
}
