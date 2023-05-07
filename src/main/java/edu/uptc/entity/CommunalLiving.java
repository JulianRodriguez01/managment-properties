package edu.uptc.entity;

public class CommunalLiving {

	private int idCommunalLiving;
	private ReservationLugars reservationLugars;
	private String description;
	private boolean disponibility;

	public int getIdCommunalLiving() {
		return idCommunalLiving;
	}

	public void setIdCommunalLiving(int idCommunalLiving) {
		this.idCommunalLiving = idCommunalLiving;
	}

	public ReservationLugars getReservationLugars() {
		return reservationLugars;
	}

	public void setReservationLugars(ReservationLugars reservationLugars) {
		this.reservationLugars = reservationLugars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisponibility() {
		return disponibility;
	}

	public void setDisponibility(boolean disponibility) {
		this.disponibility = disponibility;
	}

}