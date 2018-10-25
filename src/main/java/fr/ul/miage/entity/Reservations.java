package fr.ul.miage.entity;

import java.util.ArrayList;
import java.util.Date;

public class Reservations {
	
	private ArrayList<Reservation> Elements = new ArrayList();

	public Reservations(ArrayList<Reservation> elements) {
		super();
		Elements = elements;
	}

	public Reservations() {
		super();
	}

	public ArrayList<Reservation> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Reservation> elements) {
		Elements = elements;
	}
	
	public Reservation find(Oeuvre oeuvre, Usager usager) {
		for(Reservation elt : Elements) {
			if(elt.getOeuvre().equals(oeuvre) && elt.getUsager().equals(usager)) {
				return elt;
			}
		}
		return null;
	}
	
	public void create(Usager usager, Oeuvre oeuvre, Date date) {
		Elements.add(new Reservation(usager,oeuvre,date));
	}
	
	public void annul(Reservation res) {
		Elements.remove(res);
	}
}
