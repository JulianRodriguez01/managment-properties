package edu.uptc.entity;

public class HorizontalPropertie {

	private int id;
	private String namePropertie;
	private String block;
	private String apartment;
	private String adicionalInformation;

	public HorizontalPropertie() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamePropertie() {
		return namePropertie;
	}

	public void setNamePropertie(String namePropertie) {
		this.namePropertie = namePropertie;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getAdicionalInformation() {
		return adicionalInformation;
	}

	public void setAdicionalInformation(String adicionalInformation) {
		this.adicionalInformation = adicionalInformation;
	}
}