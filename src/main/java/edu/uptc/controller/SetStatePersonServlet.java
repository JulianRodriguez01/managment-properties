package edu.uptc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uptc.model.Manager;
import edu.uptc.utilities.Utilitie;

@WebServlet("/SetStatePersonServlet")
public class SetStatePersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public SetStatePersonServlet() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			goSetSate(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void goSetSate(HttpServletRequest request, HttpServletResponse response) {
		try {
			manager.changeState(Integer.parseInt(request.getParameter("select_id")), Utilitie.getStateAccount(request.getParameter("select_state")));
			response.setHeader("refresh", "0;url=pages/admin/correctReport.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}