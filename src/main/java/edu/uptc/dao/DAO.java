package edu.uptc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.uptc.entity.CommunalLiving;
import edu.uptc.entity.DetailsPayment;
import edu.uptc.entity.HorizontalPropertie;
import edu.uptc.entity.Person;
import edu.uptc.entity.Reservation;
import edu.uptc.entity.Obligation;
import edu.uptc.entity.Roles;
import edu.uptc.utilities.Utilitie;

public class DAO {

	public static final String BD_PROPERTIES = "/properties";
	private String maquina = "localhost";
	private String usuario = "root";
	private String clave = "";
	private int puerto = 3306;

	public ArrayList<Person> fillListPerson() throws SQLException, ClassNotFoundException {
		ArrayList<Person> listPersonOut = new ArrayList<>();
		Statement stmt = null;
		String query = "select * from person";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES,
				this.usuario, this.clave);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Person person = new Person();
			person.setId(rs.getInt("id_person"));
			person.setRol(Utilitie.getRol(rs.getString("rol")));
			person.setNumberID(rs.getInt("identification_number"));
			person.setName(rs.getString("name"));
			person.setLastName(rs.getString("last_name"));
			person.setPhoneNumber(rs.getString("phone_number"));
			person.setUsername(rs.getString("username"));
			person.setPassword(rs.getString("password"));
			person.setStateAccount(Utilitie.getStateAccount(rs.getString("state_account")));
			person.setHorizontalPropertie(searchPropertie(rs.getInt("id_person")));
			listPersonOut.add(person);
		}
		con.close();
		return listPersonOut;
	}

	public ArrayList<CommunalLiving> fillListCommunalLiving() throws SQLException, ClassNotFoundException {
		ArrayList<CommunalLiving> listCommunalLivingOut = new ArrayList<>();
		Statement stmt = null;
		String query = "select * from communal_living";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES,
				this.usuario, this.clave);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			CommunalLiving communalLiving = new CommunalLiving();
			communalLiving.setIdCommunalLiving(rs.getInt("id_communal_living"));
			communalLiving.setReservationLugars(Utilitie.getReservationLugars(rs.getString("reservation_lugar")));
			communalLiving.setDescription(rs.getString("description"));
			communalLiving.setDisponibility(Utilitie.getState(rs.getString("disponibility")));
			listCommunalLivingOut.add(communalLiving);
		}
		con.close();
		return listCommunalLivingOut;
	}

	public void fillListObligation(Person person) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		String query = "select * from obligation";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES,
				this.usuario, this.clave);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			if (person.getId() == rs.getInt("id_person")) {
				Obligation obligation = new Obligation();
				obligation.setIdObligation(rs.getInt("id_obligation"));
				obligation.setdescription(rs.getString("description"));
				person.addObligation(obligation);
			}
		}
		con.close();
	}

	public void fillListDetailsPayment(Person person) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		String query = "select * from details_payment";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES,
				this.usuario, this.clave);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			if (person.getId() == rs.getInt("id_person")) {
				DetailsPayment detailsPayment = new DetailsPayment();
				detailsPayment.setDescription(rs.getString("description"));
				detailsPayment.setValue(rs.getInt("value_payment"));
				detailsPayment.setDatePayment(rs.getDate("date_payment"));
				detailsPayment.setPaymentAgreement(Utilitie.getState(rs.getString("payment_agreement")));
				detailsPayment.setOutstandingBalance(rs.getDouble("outstanding_balance"));
				detailsPayment.isStatePayment(Utilitie.getState(rs.getString("state_payment")));
				person.addPaymentReceipts(detailsPayment);
			}
		}
		con.close();
	}

	public void fillListReservation(Person person, ArrayList<CommunalLiving> listCommunalLiving)
			throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		String query = "select * from reservation";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES,
				this.usuario, this.clave);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			if (person.getId() == rs.getInt("id_person")) {
				Reservation reservation = new Reservation();
				reservation.setIdReservation(rs.getInt("id_reservation"));
				reservation.setDateReservation(rs.getDate("date_reservation_in"));
				reservation.setHours(rs.getInt("hours"));
				reservation.setCancel(Utilitie.getState(rs.getString("cancel")));
				reservation
						.setCommunalLiving(searchCommunalLiving(listCommunalLiving, rs.getInt("id_communal_living")));
				person.addReservationtion(reservation);
			}
		}
		con.close();
	}

	public ArrayList<DetailsPayment> fillListDetailsPayment(int idPaymentReceipts)
			throws SQLException, ClassNotFoundException {
		ArrayList<DetailsPayment> listDetailsPayment = new ArrayList<DetailsPayment>();
		Statement stmt = null;
		String query = "select * from details_payment";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES,
				this.usuario, this.clave);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			if (idPaymentReceipts == rs.getInt("id_payment_receipts")) {
				DetailsPayment detailsPayment = new DetailsPayment();
				detailsPayment.setIdDetails(rs.getInt("id_details_payment"));
				detailsPayment.setDescription(rs.getString("description"));
				detailsPayment.setValue(rs.getDouble("value_payment"));
				detailsPayment.setDatePayment(rs.getDate("date_payment"));
				detailsPayment.setPaymentAgreement(Utilitie.getState(rs.getString("payment_agreement")));
				detailsPayment.setOutstandingBalance(rs.getDouble("outstanding_balance"));
				listDetailsPayment.add(detailsPayment);
			}
		}
		con.close();
		return listDetailsPayment;
	}

	public HorizontalPropertie searchPropertie(int idPerson) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		String query = "SELECT pr.* FROM  person pe, horizontal_propertie pr WHERE pe.id_person = pr.id_person AND pe.id_person ="
				+ idPerson + ";";
		HorizontalPropertie horizontalPropertie = new HorizontalPropertie();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES, this.usuario, this.clave);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				horizontalPropertie.setId(rs.getInt("id_horizontal_properties"));
				horizontalPropertie.setNamePropertie(rs.getString("name_propertie"));
				horizontalPropertie.setBlock(rs.getString("block"));
				horizontalPropertie.setApartment(rs.getString("apartment"));
				horizontalPropertie.setAdicionalInformation(rs.getString("aditional_information"));
			}
			con.close();
		} catch (SQLException sqlex) {
			throw sqlex;
		}
		return horizontalPropertie;
	}

	public CommunalLiving searchCommunalLiving(ArrayList<CommunalLiving> listCommunalLiving, int idCommunalLiving) {
		for (CommunalLiving communalLiving : listCommunalLiving) {
			if (communalLiving.getIdCommunalLiving() == idCommunalLiving) {
				return communalLiving;
			}
		}
		return null;
	}

	public void addPerson(Roles rol, int numberID, String name, String lastName, String phoneNumber, String username,
			String password) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO `person` (`id_person`, `rol`, `identification_number`, `name`, `last_name`, `phone_number`, `username`, `password`, `state_account`) VALUES (NULL, '"
				+ String.valueOf(rol) + "', '" + numberID + "', '" + name + "', '" + lastName + "', '" + phoneNumber
				+ "', '" + username + "', '" + password + "', 'CR');";
		executeQuery(query);
	}

	public void addReservation(int idPerson, Reservation reservation) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO `reservation` (`id_reservation`, `cancel`, `date_reservation_in`, `hours`, `id_person`, `id_communal_living`) VALUES (NULL, "
				+ "'F', '" + Utilitie.getDateFormat(reservation.getDateReservation()) + "', '" + reservation.getHours()
				+ "', '" + idPerson + "', '" + reservation.getCommunalLiving().getIdCommunalLiving() + "')";
		executeQuery(query);
	}

	public void addDetailsPayment(int idPerson, DetailsPayment detailsPayment)
			throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO `details_payment` (`id_details_payment`, `description`, `value_payment`, `date_payment`, `payment_agreement`, `outstanding_balance`, `state_payment`, `id_person`) VALUES (NULL,"
				+ "'" + detailsPayment.getDescription() + "', '" + detailsPayment.getValue() + "', '"
				+ Utilitie.getDateFormat(detailsPayment.getDatePayment()) + "', '"
				+ Utilitie.getState(detailsPayment.isPaymentAgreement()) + "', '"
				+ detailsPayment.getOutstandingBalance() + "', '" + Utilitie.getState(detailsPayment.isStatePayment())
				+ "', '" + idPerson + "');";
		executeQuery(query);
	}

	public void addObligation(Person person, Obligation obligation) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO `obligation` (`id_obligation`, `description`, `id_person`) VALUES (NULL, '"
				+ obligation.getdescription() + "', '" + person.getId() + "');";
		executeQuery(query);
	}

	public void setObligation(int idPerson, Obligation obligation) throws SQLException, ClassNotFoundException {
		String query = "UPDATE `obligation` SET `id_person` = '16' WHERE `obligation`.`id_obligation` = 27;";
		executeQuery(query);
	}

	public void updateStatePerson(int numberID, String state) throws SQLException, ClassNotFoundException {
		String query = "UPDATE `person` SET `state_account` = '" + state
				+ "'  WHERE `person`.`identification_number` = " + numberID + ";";
		executeQuery(query);
	}

	public void updatePerson(int idUpdate, Person person) throws SQLException, ClassNotFoundException {
		String query = "UPDATE `person` SET `name` = '" + person.getName() + "', `last_name` = '" + person.getLastName()
				+ "', `phone_number` = '" + person.getPhoneNumber() + "', `identification_number` = '"
				+ person.getNumberID() + "', `rol` = '" + person.getRol() + "', `username` = '" + person.getUsername()
				+ "', `password` = '" + person.getPassword() + "'  WHERE `person`.`identification_number` = " + idUpdate
				+ ";";
		executeQuery(query);
	}

	public void addPropertie(int id, HorizontalPropertie horizontalPropertie)
			throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO `horizontal_propertie` (`id_horizontal_properties`, `name_propertie`, `block`, `apartment`, `aditional_information`, `id_person`) VALUES (NULL, '"
				+ horizontalPropertie.getNamePropertie() + "', '" + horizontalPropertie.getBlock() + "', '"
				+ horizontalPropertie.getApartment() + "', '" + horizontalPropertie.getAdicionalInformation() + "', " + id + ")";
		executeQuery(query);
	}

	public void setPropertie(String name, String block, String apartment, String adicionalInformation, int idPerson)
			throws SQLException, ClassNotFoundException {
		String query = "UPDATE `propertie` SET `idPerson` = '" + idPerson + "' WHERE `propertie`.`idPropertie` = "
				+ searchPropertie(idPerson).getId() + ";";
		executeQuery(query);
	}

	public void deletePerson(int id) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM `person` WHERE `person`.`identification_number` = " + id;
		executeQuery(query);
	}

	private void executeQuery(String query) throws ClassNotFoundException, SQLException {
		Statement stmt;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://" + this.maquina + ":" + this.puerto + BD_PROPERTIES, this.usuario, this.clave);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException sqlex) {
			throw sqlex;
		}
	}
}