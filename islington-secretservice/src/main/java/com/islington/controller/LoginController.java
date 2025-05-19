package com.islington.controller;

import java.io.IOException;
import com.islington.service.LoginService;
import com.islington.utils.CookieUtil;
import com.islington.utils.SessionUtil;
import com.islington.model.AgentModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * LoginController handles login requests, interacts with the LoginService to
 * authenticate users, and handles user session and role management.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final LoginService loginService;

    /**
     * Constructor initializes the LoginService.
     */
    public LoginController() {
        this.loginService = new LoginService();
    }

    /**
     * Handles GET requests to display the login page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user login.
     *
     * @param req  HttpServletRequest object
     * @param resp HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codename = req.getParameter("codename");
        String password = req.getParameter("password");

        // Log to check if codename is being retrieved correctly
        System.out.println("Received codename: " + codename);

        // Create a UserModel object for login
        AgentModel userModel = new AgentModel(codename, password);

        // Call the LoginService to validate user credentials
        Boolean loginStatus = loginService.loginUser(userModel);

        if (loginStatus != null && loginStatus) {
            // If login is successful, set the username in session
            SessionUtil.setAttribute(req, "username", codename);
            if ("admin".equals(codename)) {
                CookieUtil.addCookie(resp, "role", "admin", 5 * 30);
                resp.sendRedirect(req.getContextPath() + "/admindash"); // Redirect to dashboard
            } else {
                CookieUtil.addCookie(resp, "role", "agent", 5 * 30);
                resp.sendRedirect(req.getContextPath() + "/home"); // Redirect to home page
            }
        } else {
            // Handle login failure
            handleLoginFailure(req, resp, loginStatus);
        }
    }

    /**
     * Handles login failure by setting an error message and forwarding to the
     * login page.
     *
     * @param req         HttpServletRequest object
     * @param resp        HttpServletResponse object
     * @param loginStatus Boolean indicating the login status (true/false)
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        // Check why the login failed
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "User credential mismatch. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
