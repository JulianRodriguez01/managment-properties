package edu.uptc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uptc.entity.Obligation;
import edu.uptc.entity.Reservation;
import edu.uptc.model.Manager;
import edu.uptc.utilities.Utilitie;

@WebServlet("/AddReservationServletUser")
public class AddReservationServletUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public AddReservationServletUser() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addReservation(request, response);
	}

	private void addReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Reservation reservation = new Reservation();
		int idCommunalLiving = Integer.parseInt(request.getParameter("select_comun"));
		Date date = Utilitie.getDateFormat("yyyy-MM-dd", request.getParameter("res_dat"));
		reservation.setDateReservation(date);
		reservation.setHours(Integer.parseInt(request.getParameter("res_hor")));
		reservation.setCommunalLiving(manager.searchCommunalLiving(idCommunalLiving));
		try {
			System.out.println(request.getParameter("select_id"));
			manager.addReservationToPerson(Integer.parseInt(request.getParameter("select_id")), reservation);
			Obligation obligation = new Obligation();
			obligation.setdescription("Deuda: " + date + "Valor de: "
					+ manager.calculateValueReservation(
							(manager.searchCommunalLiving(idCommunalLiving).getReservationLugars()), 1000000,
							Integer.parseInt(request.getParameter("res_hor"))));
			manager.addObligation(Integer.parseInt(request.getParameter("select_id")), obligation);
			goModal(response, "correct.jsp");
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			goModal(response, "correct.jsp");
		}
	}

	public void goModal(HttpServletResponse response, String path) throws IOException {
		response.setContentType("text/html");
		response.setHeader("refresh", "0;url=pages/user/" + path);
	}
}