package fr.ul.miage.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Reservation {
	
	private String ID = UUID.randomUUID().toString();
	private Date DateDemande;
	private Usager usager;
	private Oeuvre oeuvre;
	
	public Reservation(Usager usager, Oeuvre oeuvre) {
		super();
		DateDemande = Calendar.getInstance().getTime();
		this.usager = usager;
		this.oeuvre = oeuvre;
	}
	
	public Reservation(Usager usager, Oeuvre oeuvre, Date date) {
		super();
		DateDemande = date;
		this.usager = usager;
		this.oeuvre = oeuvre;
	}

	public String getID() {
		return ID;
	}

	public Date getDateDemande() {
		return DateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		DateDemande = dateDemande;
	}

	public Usager getUsager() {
		return usager;
	}

	public Oeuvre getOeuvre() {
		return oeuvre;
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
		Reservation other = (Reservation) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
}
