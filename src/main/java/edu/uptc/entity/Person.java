package edu.uptc.entity;

import java.util.ArrayList;

public class Person {

	private int id;
	private Roles rol;
	protected int numberID;
	protected String name;
	protected String lastName;
	protected String phoneNumber;
	private String username;
	private String password;
	private StateAccount stateAccount;

	private HorizontalPropertie horizontalPropertie;
	private ArrayList<Obligation> listObligations;
	private ArrayList<Reservation> listReservation;
	private ArrayList<DetailsPayment> listDetailsPayment;

	public Person() {
		listObligations = new ArrayList<Obligation>();
		listReservation = new ArrayList<Reservation>();
		listDetailsPayment = new ArrayList<DetailsPayment>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberID() {
		return numberID;
	}

	public void setNumberID(int numberID) {
		this.numberID = numberID;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StateAccount getStateAccount() {
		return stateAccount;
	}

	public void setStateAccount(StateAccount stateAccount) {
		this.stateAccount = stateAccount;
	}

	public HorizontalPropertie getHorizontalPropertie() {
		return horizontalPropertie;
	}

	public void setHorizontalPropertie(HorizontalPropertie horizontalPropertie) {
		this.horizontalPropertie = horizontalPropertie;
	}

	public ArrayList<Obligation> getListObligations() {
		return listObligations;
	}

	public void addObligation(Obligation obligation) {
		listObligations.add(obligation);
	}

	public void deleteObligation(int serchObligation) {
		listObligations.remove(serchObligation(serchObligation));
	}

	public void setObligation(int serchObligation) {
		listObligations.set(serchObligation, serchObligation(serchObligation));
	}

	public Obligation serchObligation(int idSearch) {
		for (Obligation obligation : listObligations) {
			if (obligation.getIdObligation() == idSearch)
				return obligation;
		}
		return null;
	}

	public ArrayList<Reservation> getListReservation() {
		return listReservation;
	}

	public void addReservationtion(Reservation reservation) {
		listReservation.add(reservation);
	}

	public void deleteReservation(int serchReservation) {
		listReservation.remove(serchReservation(serchReservation));
	}

	public void setReservation(int serchReservation) {
		listReservation.set(serchReservation, serchReservation(serchReservation));
	}

	public Reservation serchReservation(int idSearch) {
		for (Reservation reservation : listReservation) {
			if (reservation.getIdReservation() == idSearch)
				return reservation;
		}
		return null;
	}

	public ArrayList<DetailsPayment> getListDetailsPayment() {
		return listDetailsPayment;
	}

	public void setListDetailsPayment(ArrayList<DetailsPayment> listDetailsPayment) {
		this.listDetailsPayment = listDetailsPayment;
	}

	public void addPaymentReceipts(DetailsPayment detailsPayment) {
		listDetailsPayment.add(detailsPayment);
	}

	public void deletePaymentReceipts(int serchPaymentReceipts) {
		listDetailsPayment.remove(serchPaymentReceipts(serchPaymentReceipts));
	}

	public void setPaymentReceipts(int serchPaymentReceipts) {
		listDetailsPayment.set(serchPaymentReceipts, serchPaymentReceipts(serchPaymentReceipts));
	}

	public DetailsPayment serchPaymentReceipts(int idSearch) {
		for (DetailsPayment detailsPayment : listDetailsPayment) {
			if (detailsPayment.getIdDetails() == idSearch)
				return detailsPayment;
		}
		return null;
	}
}