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

@WebServlet("/AdminPerson")
public class AdminPerson extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public AdminPerson() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		if (request.getParameter("delete_user") != null && Integer.valueOf(request.getParameter("delete_user")) > 0) {
			goDelete(Integer.valueOf(request.getParameter("delete_user")));
			response.setContentType("text/html");
			response.setHeader("refresh", "0;url=pages/admin/correctReport.jsp");
		} else if (request.getParameter("update_user") != null && Integer.valueOf(request.getParameter("update_user")) > 0) {
			response.setContentType("text/html");
			id = Integer.valueOf(request.getParameter("update_user"));
			response.setHeader("refresh", "0;url=pages/admin/modifyUser.jsp");
		} else if(Integer.valueOf(request.getParameter("update_user")) == -1) {			
			goUpdate(request, id);
		}		
	}

	public void goDelete(int id) {
		try {
			manager.deletePerson(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goUpdate(HttpServletRequest request, int id) {
		Person personAdd = new Person();
		personAdd.setRol(request.getParameter("per_rol").equals("admin") ? Roles.ADMIN : Roles.USER);
		personAdd.setNumberID(Integer.valueOf(request.getParameter("per_id")));
		personAdd.setName(request.getParameter("per_name"));
		personAdd.setLastName(request.getParameter("per_last_name"));
		personAdd.setPhoneNumber(request.getParameter("per_phone"));
		personAdd.setUsername(request.getParameter("per_username"));
		personAdd.setPassword(request.getParameter("per_password"));
		try {
			manager.updatePerson(id, personAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}