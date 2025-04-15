package com.islington.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.islington.utils.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RegisterController handles user registration requests.
 * This version only performs form validation using ValidationUtil.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String validationMessage = validateRegistrationForm(req);

		if (validationMessage != null) {
			handleError(req, resp, validationMessage);
		} else {
			req.setAttribute("success", "Your account is valid (fake success for testing)!");
			req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
		}
	}

	private String validateRegistrationForm(HttpServletRequest req) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("phoneNumber");
		String subject = req.getParameter("subject");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("retypePassword");

		if (ValidationUtil.isNullOrEmpty(firstName)) return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName)) return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(username)) return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr)) return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(gender)) return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(email)) return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(number)) return "Phone number is required.";
		if (ValidationUtil.isNullOrEmpty(subject)) return "Subject is required.";
		if (ValidationUtil.isNullOrEmpty(password)) return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword)) return "Please retype the password.";

		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";
		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old to register.";

		return null; // All validations passed
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("phoneNumber", req.getParameter("phoneNumber"));
		req.setAttribute("subject", req.getParameter("subject"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}
}
