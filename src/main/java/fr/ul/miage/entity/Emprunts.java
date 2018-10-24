package fr.ul.miage.entity;

import java.util.ArrayList;

public class Emprunts {

	private ArrayList<Emprunt> Elements = new ArrayList();

	public Emprunts(ArrayList<Emprunt> elements) {
		super();
		Elements = elements;
	}
	
	public Emprunts() {
		super();
	}

	public ArrayList<Emprunt> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Emprunt> elements) {
		Elements = elements;
	}
	
	
	
	public Emprunt findOnID(Usager usager, Exemplaire exemplaire) {
		for (Emprunt elt : Elements) {
			if(elt.getUsager().equals(usager) && elt.getExeplaire().equals(exemplaire)) {
				return elt;
			}
		}
		return null;
	}
	
}
