package edu.uptc.entity;

import java.util.Date;

public class Reservation {

	private int idReservation;
	private boolean cancel;
	private CommunalLiving CommunalLiving;
	private Date dateReservation;
	private int hours;

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public CommunalLiving getCommunalLiving() {
		return CommunalLiving;
	}

	public void setCommunalLiving(CommunalLiving communalLiving) {
		CommunalLiving = communalLiving;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
}