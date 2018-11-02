package fr.ul.miage.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Exemplaire {

	private String ID = UUID.randomUUID().toString();
	private Oeuvre Oeuvre;
	private String Vices = "";
	private Date DateAcquisition = Calendar.getInstance().getTime();
	
	public Exemplaire(fr.ul.miage.entity.Oeuvre oeuvre) {
		super();
		Oeuvre = oeuvre;
	}

	public Exemplaire(String iD, Oeuvre oeuvre, String vices, Date dateAcquisition) {
		super();
		ID = iD;
		Oeuvre = oeuvre;
		Vices = vices;
		DateAcquisition = dateAcquisition;
	}

	public String getID() {
		return ID;
	}

	public Oeuvre getOeuvre() {
		return Oeuvre;
	}

	public String getVices() {
		return Vices;
	}

	public void setVices(String vices) {
		Vices = vices;
	}
	
	public void addVices(String vices) {
		Vices = Vices + vices;
	}

	public Date getDateAcquisition() {
		return DateAcquisition;
	}

	public void setDateAcquisition(Date dateAcquisition) {
		DateAcquisition = dateAcquisition;
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
		Exemplaire other = (Exemplaire) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
	
}
