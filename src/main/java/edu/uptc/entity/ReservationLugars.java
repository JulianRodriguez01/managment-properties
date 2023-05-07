package edu.uptc.entity;

public enum ReservationLugars {

	HALL("Salon", 10000), FIELD("Cancha", 50000), OFFICINE("Oficina", 5000), RESTAURANT("Restaurante", 90000),
	PUBLIC("Publico", 0);

	private String text;
	private double aditionalCost;

	ReservationLugars(String text, double aditionalCost) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public double getAditionalCost() {
		return aditionalCost;
	}
}