package edu.uptc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uptc.entity.Person;
import edu.uptc.entity.Roles;
import edu.uptc.model.Manager;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Manager manager;

	public LoginServlet() {
		try {
			manager = new Manager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("logout") != null) {
			closeSession(request, response);
		}
		String username = request.getParameter("per_name");
		String password = request.getParameter("per_password");
		Person per = manager.login(username, password);
		if (per != null) {
			if (per.getRol().equals(Roles.USER)) {
				System.out.println("llegaaaaaa");
				createSession(request, response, per, "pages/user/index.jsp");
			} else {
				createSession(request, response, per, "pages/admin/index.jsp");
			}
		} else {
			goMainError(response);
		}
	}

	public void createSession(HttpServletRequest request, HttpServletResponse response, Person person, String redirect) {
		HttpSession httpSession = request.getSession(true);
		httpSession.setMaxInactiveInterval(60);
		httpSession.setAttribute("person", person);
		try {
			response.sendRedirect(redirect);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("person", null);
		httpSession.invalidate();
		try {
			response.sendRedirect("pages/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void goMainError(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setHeader("refresh", "0;url=pages/admin/404.jsp");
	}
}