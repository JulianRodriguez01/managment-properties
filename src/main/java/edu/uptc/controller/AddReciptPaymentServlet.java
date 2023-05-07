package edu.uptc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uptc.entity.DetailsPayment;
import edu.uptc.model.Manager;
import edu.uptc.utilities.Utilitie;

@WebServlet("/AddReciptPaymentServlet")
public class AddReciptPaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public AddReciptPaymentServlet() throws ClassNotFoundException, SQLException {
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addReciptPayment(request, response);
	}

	private void addReciptPayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DetailsPayment detailsPayment = new DetailsPayment();
		detailsPayment.setDescription(request.getParameter("re_description"));
		detailsPayment.setValue(Double.parseDouble(request.getParameter("re_value")));
		detailsPayment.setDatePayment(Utilitie.getDateFormat("yyyy-MM-dd", request.getParameter("re_date")));
		detailsPayment.setPaymentAgreement(request.getParameter("re_PaymentAgreement") != null
				&& request.getParameter("re_PaymentAgreement").equals("T") ? true : false);
		detailsPayment.setOutstandingBalance(Double.parseDouble(request.getParameter("re_oustanding")));
		detailsPayment.isStatePayment(
				request.getParameter("re_state") != null && request.getParameter("re_state").equals("T") ? true
						: false);
		try {
			manager.addPaymentToPerson(Integer.parseInt(request.getParameter("select_id")), detailsPayment);
			goModal(response, "correctReport.jsp");
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			goModal(response, "404.jsp");
		}
	}

	public void goModal(HttpServletResponse response, String path) throws IOException {
		response.setContentType("text/html");
		response.setHeader("refresh", "0;url=pages/admin/" + path);
	}
}