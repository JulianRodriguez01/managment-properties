package edu.uptc.entity;

public enum StateAccount {

	CREATED("Creado", "CR"), UP_TO_DATE("Al día", "UD"), IN_DEBT("Con deuda","ID"), MOROSO("Moroso", "MO"), IN_PAYMENT_AGREEMENT("En acuerdo de pago", "IP"), COACTIVE_COLLECTION("Cobro coáctivo", "CO");

	private String text;
	private String abreviature;

	StateAccount(String text, String abreviature) {
		this.text = text;
		this.abreviature = abreviature;
	}

	public String getText() {
		return text;
	}
	
	public String getAbreviature() {
		return abreviature;
	}
}
