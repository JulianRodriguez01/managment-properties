package edu.uptc.model;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uptc.dao.DAO;
import edu.uptc.entity.CommunalLiving;
import edu.uptc.entity.DetailsPayment;
import edu.uptc.entity.HorizontalPropertie;
import edu.uptc.entity.Obligation;
import edu.uptc.entity.Person;
import edu.uptc.entity.Reservation;
import edu.uptc.entity.ReservationLugars;
import edu.uptc.entity.StateAccount;

public class Manager {

	private DAO dao;
	private ArrayList<Person> personList;
	private ArrayList<CommunalLiving> communalLivingList;

	public Manager() throws ClassNotFoundException, SQLException {
		initVariables();
	}

	private void initVariables() throws ClassNotFoundException, SQLException {
		dao = new DAO();
		personList = dao.fillListPerson();
		communalLivingList = dao.fillListCommunalLiving();
		joinPersonColumns();
	}

	public void joinPersonColumns() throws ClassNotFoundException, SQLException {
		for (Person person : personList) {
			dao.fillListObligation(person);
			dao.fillListDetailsPayment(person);
			dao.fillListObligation(person);
			dao.fillListReservation(person, communalLivingList);
		}
	}

	public ArrayList<Person> getPersonList() {
		return personList;
	}

	public ArrayList<Reservation> getReservationAllList() {
		ArrayList<Reservation> listOut = new ArrayList<Reservation>();
		for (Person person : personList) {
			for (Reservation reservation : person.getListReservation()) {
				listOut.add(reservation);
			}
		}
		ordListReservation(listOut);
		return listOut;
	}

	public void addPerson(Person person) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
//		person.setPassword(EncriptKeys.encript(person.getPassword()));
		personList.add(person);
		dao.addPerson(person.getRol(), person.getNumberID(), person.getName(), person.getLastName(),
				person.getPhoneNumber(), person.getUsername(), person.getPassword());
	}

	public void addHorizontalPropertie(int person, HorizontalPropertie horizontalPropertie)
			throws ClassNotFoundException, SQLException {
		searchPerson(person).setHorizontalPropertie(horizontalPropertie);
		dao.addPropertie(person, horizontalPropertie);
	}

	public void deletePerson(int numberID) throws Exception {
		personList.remove(searchPerson(numberID));
		dao.deletePerson(numberID);
	}

	public void updatePerson(int numberUpdate, Person person) throws Exception {
		searchPerson(numberUpdate).setName(person.getName());
		searchPerson(numberUpdate).setLastName(person.getLastName());
		searchPerson(numberUpdate).setRol(person.getRol());
		searchPerson(numberUpdate).setPhoneNumber(person.getPhoneNumber());
		searchPerson(numberUpdate).setUsername(person.getUsername());
		searchPerson(numberUpdate).setPassword(person.getPassword());
		searchPerson(numberUpdate).setNumberID(person.getNumberID());
		dao.updatePerson(numberUpdate, person);
	}

	public CommunalLiving searchCommunalLiving(int idSearch) {
		for (CommunalLiving communalLiving : communalLivingList) {
			if (idSearch == communalLiving.getIdCommunalLiving()) {
				return communalLiving;
			}
		}
		return null;
	}

	public void generatePaymentReceipts(int idResident, DetailsPayment detailsPayment) throws Exception {
		searchPerson(idResident).addPaymentReceipts(detailsPayment);
	}

	public boolean generatePeaceAndSave(int numberID) throws Exception {
		Person person = searchPerson(numberID);
		ArrayList<DetailsPayment> listDetailsPayment = person.getListDetailsPayment();
		double valueDbt = 0;
		if (listDetailsPayment != null) {
			for (DetailsPayment detailsPayment : listDetailsPayment) {
				valueDbt += detailsPayment.getOutstandingBalance();
			}
		}
		return valueDbt == 0 ? true : false;
	}

	public ArrayList<Obligation> uploadObligation(int idResident) throws Exception {
		return searchPerson(idResident).getListObligations();
	}

	public void addObligation(int numberId, Obligation obligation) throws ClassNotFoundException, SQLException {
		personList.get(searchPersonNumber(numberId)).addObligation(obligation);
		dao.addObligation(searchPerson(numberId), obligation);
	}

	public ArrayList<DetailsPayment> generateHistorialPaymentReceipts(int idResident) throws Exception {
		return searchPerson(idResident).getListDetailsPayment();
	}

	public void addPaymentToPerson(int numberID, DetailsPayment detailsPayment)
			throws ClassNotFoundException, SQLException {
		searchPerson(numberID).addPaymentReceipts(detailsPayment);
		Person person = searchPerson(numberID);
		dao.addDetailsPayment(person.getId(), detailsPayment);
	}

	public Person searchPerson(int numberID) {
		for (Person person : personList) {
			if (person.getNumberID() == numberID)
				return person;
		}
		return null;
	}

	public int searchPersonNumber(int numberID) {
		int pos = 0;
		for (Person person : personList) {
			if (person.getNumberID() == numberID) {
				return pos;
			}
			pos++;
		}
		return -1;
	}

	public void addReservationToPerson(int numberID, Reservation reservation)
			throws ClassNotFoundException, SQLException {
		personList.get(searchPersonNumber(numberID)).addReservationtion(reservation);
		Person person = searchPerson(numberID);
		dao.addReservation(person.getId(), reservation);
	}

	public void registerReservation(int numberID, Reservation reservation) throws Exception {
		searchPerson(numberID).addReservationtion(reservation);
	}

	public boolean consultateDisponibilityPlace(int idCommunalLiving) {
		for (CommunalLiving communalLiving : communalLivingList) {
			if (communalLiving.getIdCommunalLiving() == idCommunalLiving && communalLiving.isDisponibility()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<CommunalLiving> getCommunalLivingList() {
		return communalLivingList;
	}

	public double calculateValuePenaltyFee(ReservationLugars reservationLugars, boolean manyFiveDay, double valueSMMV,
			int hours) {
		Double value = (manyFiveDay) ? calculateValueReservation(reservationLugars, valueSMMV, hours) / 2 : 0;
		return value;
	}

	public double calculateValueReservation(ReservationLugars reservationLugars, double valueSMMV, int hours) {
		return ((valueSMMV / 30) * hours) + reservationLugars.getAditionalCost();
	}

	public StateAccount getStateConsiderationPerson(int numberID) throws Exception {
		Person person = searchPerson(numberID);
		if (person.getListObligations() != null) {
			return StateAccount.IN_DEBT;
		} else if (person.getListObligations() == null) {
			return StateAccount.UP_TO_DATE;
		}
		return null;
	}

	public void changeState(int idResident, StateAccount stateAccount) throws Exception {
		searchPerson(idResident).setStateAccount(stateAccount);
		dao.updateStatePerson(idResident, stateAccount.getAbreviature());
	}

	public Person login(String username, String password) {
		for (Person person : personList) {
			if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
				return person;
			}
		}
		return null;
	}

	public static void ordListReservation(ArrayList<Reservation> reservationList) {
		Reservation aux;
		for (int i = 0; i < reservationList.size() - 1; i++) {
			for (int j = 0; j < reservationList.size() - i - 1; j++) {
				if (reservationList.get(j + 1).getIdReservation() < reservationList.get(j).getIdReservation()) {
					aux = reservationList.get(j + 1);
					reservationList.set(j + 1, reservationList.get(j));
					reservationList.set(j, aux);
				}
			}
		}
	}
}
