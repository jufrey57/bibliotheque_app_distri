package fr.ul.miage.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Emprunt {

	private String ID = UUID.randomUUID().toString();
	private Date DateDebut = new Date();
	private Date DateRetour = new Date();
	private Usager Usager;
	private Exemplaire Exeplaire;
	
	public Emprunt(Date dateDebut, Usager usager, Exemplaire exeplaire) {
		super();
		DateDebut = dateDebut;
		Usager = usager;
		Exeplaire = exeplaire;
	}
	
	public Emprunt(Usager usager, Exemplaire exeplaire) {
		super();
		DateDebut = Calendar.getInstance().getTime();
		Usager = usager;
		Exeplaire = exeplaire;
	}

	public Emprunt(String iD, Date dateDebut, Date dateRetour, Usager usager, Exemplaire exeplaire) {
		super();
		ID = iD;
		DateDebut = dateDebut;
		DateRetour = dateRetour;
		Usager = usager;
		Exeplaire = exeplaire;
	}

	public String getID() {
		return ID;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public Date getDateRetour() {
		return DateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		DateRetour = dateRetour;
	}

	public Usager getUsager() {
		return Usager;
	}

	public Exemplaire getExeplaire() {
		return Exeplaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprunt other = (Emprunt) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
	
}
