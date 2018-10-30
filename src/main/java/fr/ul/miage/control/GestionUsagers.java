package fr.ul.miage.control;

import fr.ul.miage.entity.Usager;
import fr.ul.miage.entity.Usagers;
import main.java.fr.ul.miage.db.DbConnector;

public class GestionUsagers {
	
	private Usagers usagers;

	public Usager creerUsager(String nom, String prenom, int age, String mail) {
		Usager u = new Usager(nom,prenom,age,mail);
		usagers.getElements().add(u);
		DbConnector.putUsager(u);
		return u;
	}
	
	public Usager findUsager(Usager usager) {
		return usagers.find(usager);
	}
	
	public Usager findUsager(String nom, String prenom) {
		return usagers.find(nom, prenom);
	}
	
	public void detruireUsager(Usager usager) {
		if(usagers.getElements().contains(usager)) {
			usagers.getElements().remove(usager);
			DbConnector.delUsager(usager.getID());
		}
	}
	
	public void notify(Usager usager) {
		//Envoie un mail à l'adresse mail de l'usager
	}
}
