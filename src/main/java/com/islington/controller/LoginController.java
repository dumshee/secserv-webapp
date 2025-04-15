package com.islington.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		boolean isAuthenticated = checkCredentials(username, password);

		if (isAuthenticated) {
			// Redirect based on role (simple condition)
			if ("admin".equals(username)) {
				resp.sendRedirect(req.getContextPath() + "/dashboard");
			} else {
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} else {
			req.setAttribute("error", "Invalid username or password. Please try again.");
			req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
		}
	}

	private boolean checkCredentials(String username, String password) {
		// Hardcoded credentials
		return ("admin".equals(username) && "admin123".equals(password)) ||
		       ("user".equals(username) && "user123".equals(password));
	}
}
