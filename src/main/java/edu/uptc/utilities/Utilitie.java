package edu.uptc.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.uptc.entity.ReservationLugars;
import edu.uptc.entity.Roles;
import edu.uptc.entity.StateAccount;

public class Utilitie {

	public static Roles getRol(String text) {
		return text.equalsIgnoreCase("ADMIN") ? Roles.ADMIN : Roles.USER;
	}

	public static StateAccount getStateAccount(String text) {
		switch (text) {
		case "CR":
			return StateAccount.CREATED;
		case "UD":
			return StateAccount.UP_TO_DATE;
		case "ID":
			return StateAccount.IN_DEBT;
		case "MO":
			return StateAccount.MOROSO;
		case "IP":
			return StateAccount.IN_PAYMENT_AGREEMENT;
		default:
			return StateAccount.COACTIVE_COLLECTION;
		}
	}

	public static String getStateAccountText(StateAccount stateAccount) {
		switch (stateAccount) {
		case CREATED:
			return "CR";
		case UP_TO_DATE:
			return "UD";
		case IN_DEBT:
			return "ID";
		case MOROSO:
			return "MO";
		case IN_PAYMENT_AGREEMENT:
			return "IP";
		default:
			return "CO";
		}
	}

	public static ReservationLugars getReservationLugars(String text) {
		switch (text) {
		case "HA":
			return ReservationLugars.HALL;
		case "FI":
			return ReservationLugars.FIELD;
		case "OF":
			return ReservationLugars.OFFICINE;
		case "RE":
			return ReservationLugars.RESTAURANT;
		case "PU":
			return ReservationLugars.PUBLIC;
		default:
			return null;
		}
	}

	public static String getReservationLugarsText(ReservationLugars reservationLugars) {
		switch (reservationLugars) {
		case HALL:
			return "HA";
		case FIELD:
			return "FI";
		case OFFICINE:
			return "OF";
		case RESTAURANT:
			return "OF";
		case PUBLIC:
			return "RE";
		default:
			return null;
		}
	}

	public static Date getDateFormat(String formatPattern, String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateFormat(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public static boolean getState(String text) {
		return (text.equals("T")) ? true : false;
	}
	
	public static char getState(boolean state) {
		return (state) ? 'T' : 'F';
	}
}