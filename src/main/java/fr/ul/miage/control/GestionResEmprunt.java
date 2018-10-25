package fr.ul.miage.control;



import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.ul.miage.entity.Emprunt;
import fr.ul.miage.entity.Emprunts;
import fr.ul.miage.entity.Exemplaire;
import fr.ul.miage.entity.Exemplaires;
import fr.ul.miage.entity.Oeuvre;
import fr.ul.miage.entity.Oeuvres;
import fr.ul.miage.entity.Reservation;
import fr.ul.miage.entity.Reservations;
import fr.ul.miage.entity.Usager;
import fr.ul.miage.entity.Usagers;

public class GestionResEmprunt {

	private Emprunts emprunts;
	private Reservations reservations;
	private Usagers usagers;
	private Oeuvres oeuvres;
	private Exemplaires exemplaires;
	private GestionUsagers gestionUsagers;
	
	private static final Logger LOGGER = Logger.getLogger(GestionResEmprunt.class.getName());
	
	public void reserver(String nom, String prenom, String titre) {
		Usager usager = usagers.find(nom, prenom);
		Oeuvre oeuvre = oeuvres.findOnTitle(titre);
		if(usager!=null && oeuvre!=null) {
			Reservation res = new Reservation(usager,oeuvre);
			reservations.getElements().add(res);
			LOGGER.log(Level.FINEST, "Reservation bien effectuée");
		}else {
			if(usager==null) {
				LOGGER.log(Level.FINE, "Usager non trouvé");
			}
			if(oeuvre==null) {
				LOGGER.log(Level.FINE, "Oeuvre non trouvée");
			}
		}
	}
	
	public void emprunter(String nom, String prenom, String titre) {
		Usager usager = usagers.find(nom, prenom);
		Oeuvre oeuvre = oeuvres.findOnTitle(titre);
		if(usager!=null && oeuvre!=null) {
			ArrayList<Exemplaire> ex = exemplaires.findOnOeuvre(oeuvre);
			if(ex.isEmpty()) {
				LOGGER.log(Level.FINE, "Aucun exemplaire trouvé pour l'oeuvre "+titre);
			}else {
				annulerRes(usager,oeuvre);
				Emprunt emp = new Emprunt(usager,ex.get(0));
				emprunts.getElements().add(emp);
				LOGGER.log(Level.FINEST, "Emprunt bien effectué");
			}
		}else {
			if(usager==null) {
				LOGGER.log(Level.FINE, "Usager non trouvé");
			}
			if(oeuvre==null) {
				LOGGER.log(Level.FINE, "Oeuvre non trouvée");
			}
		}
	}
	
	public void annulerRes(Usager usager, Oeuvre oeuvre) {
		Usager u = usagers.find(usager);
		Oeuvre o = oeuvres.findOnID(oeuvre.getID());
		if(o!=null && u!=null) {
			reservations.annul(reservations.find(o, u));
		}else {
			if(u==null) {
				LOGGER.log(Level.FINE, "Usager non trouvé");
			}
			if(o==null) {
				LOGGER.log(Level.FINE, "Oeuvre non trouvée");
			}
		}
	}
			
	
	public void creerRendu(String nomOeuvre, String nomUsager, String prenomUsager, String special, boolean retard) {
		Usager usager = usagers.find(nomUsager, prenomUsager);
		Oeuvre oeuvre = oeuvres.findOnTitle(nomOeuvre);
		if(usager!=null && oeuvre!=null) {
			ArrayList<Exemplaire> ex = exemplaires.findOnOeuvre(oeuvre);
			if(ex.isEmpty()) {
				LOGGER.log(Level.FINE, "Aucun exemplaire trouvé pour l'oeuvre "+nomOeuvre);
			}
			for(Exemplaire elt : ex) {
				Emprunt emprunt = emprunts.find(usager, elt);
				//vices
				exemplaires.findOnID(emprunt.getExeplaire().getID()).addVices(special);
				//retard
				if(retard) {
					usagers.find(usager).addRetard();
				}
				//destruction d'emprunt
				emprunts.getElements().remove(emprunt);
				//notify
				ArrayList<Emprunt> e = emprunts.find(oeuvre);
				if(e.isEmpty()) {
					LOGGER.log(Level.FINEST, "Aucun emprunt");
				}else {
					for(Emprunt elt2:e) {
						gestionUsagers.notify(elt2.getUsager());
					}
				}
			}
			
		}else {
			if(usager==null) {
				LOGGER.log(Level.FINE, "Usager non trouvé");
			}
			if(oeuvre==null) {
				LOGGER.log(Level.FINE, "Oeuvre non trouvée");
			}
		}
	}
}
