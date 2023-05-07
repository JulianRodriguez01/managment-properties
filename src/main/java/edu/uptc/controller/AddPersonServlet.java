package edu.uptc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uptc.entity.HorizontalPropertie;
import edu.uptc.entity.Person;
import edu.uptc.entity.Roles;
import edu.uptc.model.Manager;

@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public AddPersonServlet() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addPerson(request, response);
	}

	private void addPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Person personAdd = new Person();
		personAdd.setRol(request.getParameter("per_rol").equals("admin") ? Roles.ADMIN : Roles.USER);
		personAdd.setNumberID(Integer.valueOf(request.getParameter("per_id")));
		personAdd.setName(request.getParameter("per_name"));
		personAdd.setLastName(request.getParameter("per_last_name"));
		personAdd.setPhoneNumber(request.getParameter("per_phone"));
		personAdd.setUsername(request.getParameter("per_username"));
		personAdd.setPassword(request.getParameter("per_password"));
		HorizontalPropertie horizontalPropertie = createPropertie(request);
		personAdd.setHorizontalPropertie(horizontalPropertie);
		try {
			manager.addPerson(personAdd);
			manager.addHorizontalPropertie(Integer.parseInt(request.getParameter("per_id")), horizontalPropertie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		goModal(response);
	}

	private HorizontalPropertie createPropertie(HttpServletRequest request) {
		HorizontalPropertie horizontalPropertie = new HorizontalPropertie();
		horizontalPropertie.setNamePropertie(request.getParameter("pro_name"));
		horizontalPropertie.setBlock(request.getParameter("pro_blo"));
		horizontalPropertie.setApartment(request.getParameter("pro_apt"));
		horizontalPropertie.setAdicionalInformation(request.getParameter("pro_adic"));
		return horizontalPropertie;
	}

	public void goModal(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setHeader("refresh", "0;url=pages/admin/correct.jsp");
	}
}