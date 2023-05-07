package edu.uptc.entity;

import java.util.Date;

public class DetailsPayment {

	private int idDetails;
	private String description;
	private double value;
	private Date datePayment;
	private boolean paymentAgreement;
	private double outstandingBalance;
	private boolean statePayment;

	public int getIdDetails() {
		return idDetails;
	}

	public void setIdDetails(int idDetails) {
		this.idDetails = idDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public boolean isPaymentAgreement() {
		return paymentAgreement;
	}

	public void setPaymentAgreement(boolean paymentAgreement) {
		this.paymentAgreement = paymentAgreement;
	}

	public double getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}
	
	public boolean isStatePayment() {
		return statePayment;
	}

	public void isStatePayment(boolean statePayment) {
		this.statePayment = statePayment;
	}
}