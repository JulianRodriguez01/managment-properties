package edu.uptc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uptc.entity.Person;
import edu.uptc.entity.Roles;
import edu.uptc.model.Manager;

@WebServlet("/SetPersonServlet")
public class SetPersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public SetPersonServlet() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			goSetPerson(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void goSetPerson(HttpServletRequest request, HttpServletResponse response) {
		Person personSet = new Person();
		personSet.setRol(request.getParameter("edi_rol").equals("admin") ? Roles.ADMIN : Roles.USER);
		personSet.setNumberID(Integer.valueOf(request.getParameter("edi_document")));
		personSet.setName(request.getParameter("edi_name"));
		personSet.setLastName(request.getParameter("edi_last_name"));
		personSet.setPhoneNumber(request.getParameter("edi_phone"));
		personSet.setUsername(request.getParameter("edi_username"));
		personSet.setPassword(request.getParameter("edi_password"));
		try {
			manager.updatePerson(Integer.parseInt(request.getParameter("select_id")), personSet);
			response.setHeader("refresh", "0;url=pages/admin/correctReport.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}