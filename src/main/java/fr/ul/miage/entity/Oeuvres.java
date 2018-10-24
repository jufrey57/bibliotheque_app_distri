package fr.ul.miage.entity;

import java.util.ArrayList;

public class Oeuvres {
	
	private ArrayList<Oeuvre> Elements = new ArrayList();

	public Oeuvres(ArrayList<Oeuvre> elements) {
		super();
		Elements = elements;
	}
	
	public Oeuvres() {
		super();
	}

	public ArrayList<Oeuvre> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Oeuvre> elements) {
		Elements = elements;
	}
	
	public ArrayList<Oeuvre> findOnTitle(String title) {
		ArrayList<Oeuvre> res = new ArrayList();
		for (Oeuvre elt : Elements) {
			if(elt.getNom().equals(title)) {
				res.add(elt);
			}
		}
		return res;
	}
	
	public Oeuvre findOnID(String id) {
		for (Oeuvre elt : Elements) {
			if(elt.getID().equals(id)) {
				return elt;
			}
		}
		return null;
	}
	
	public Oeuvre findOnReservation(Reservation res) {
		for (Oeuvre elt : Elements) {
			if(elt.getID().equals(res.getOeuvre().getID())) {
				return elt;
			}
		}
		return null;
	}
}
