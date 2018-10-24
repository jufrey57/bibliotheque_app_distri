package fr.ul.miage.entity;

import java.util.ArrayList;

public class Usagers {
	
	private ArrayList<Usager> Elements = new ArrayList();

	public Usagers(ArrayList<Usager> elements) {
		super();
		Elements = elements;
	}

	public Usagers() {
		super();
	}

	public ArrayList<Usager> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Usager> elements) {
		Elements = elements;
	}
	
	public ArrayList<Usager> find(String nom, String prenom){
		ArrayList<Usager> res = new ArrayList();
		for(Usager elt : Elements) {
			if(elt.getNom().equals(nom) && elt.getPrenom().equals(prenom)) {
				res.add(elt);
			}
		}
		return res;
	}
	
	public Usager find(Usager usager){
		if(Elements.contains(usager)) {
			return Elements.get(Elements.indexOf(usager));
		}else {
			return null;
		}
	}
}
