package edu.uptc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uptc.model.Manager;

@WebServlet("/DisponibilityServletUser")
public class DisponibilityServletUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public DisponibilityServletUser() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			boolean state = manager.consultateDisponibilityPlace(Integer.parseInt(request.getParameter("select_id")));
			if(state) {
				goCorrect(response);
			}else {
				goFailed(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void goCorrect(HttpServletResponse response) {
		response.setHeader("refresh", "0;url=pages/user/available.jsp");
	}
	
	private void goFailed(HttpServletResponse response) {
		response.setHeader("refresh", "0;url=pages/user/unavailable.jsp");
	}
}