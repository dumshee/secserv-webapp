package com.islington.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.service.*;

/**
 * Servlet implementation for handling user modification-related HTTP requests.
 * 
 * This servlet manages the actions of deactivating, deleting, or resetting a user's
 * password. It communicates with the UserService to perform the necessary actions
 * and forwards results to the modify_users.jsp page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/modifydash" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
maxFileSize = 1024 * 1024 * 10,      // 10 MB
maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class ModifyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of UserService for handling user-related business logic
    private UserService userService;

    /**
     * Default constructor initializes the UserService instance.
     */
    public ModifyController() {
        this.userService = new UserService();
    }

    /**
     * Handles HTTP POST requests by processing the user's action (deactivate, delete, reset)
     * and forwarding the request to the modify_users.jsp page with the appropriate message.
     * 
     * @param request  The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException      If an input or output error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data from the request
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String notes = request.getParameter("notes");

        String message = "";

        // Check if the necessary parameters are present
        if (username == null || username.isEmpty()) {
            message = "Username is required!";
        } else if (action == null || action.isEmpty()) {
            message = "Please select an action!";
        } else {
            // Handle the action based on the user's selection
            switch (action) {
                case "deactivate":
                    boolean deactivated = userService.deactivateUser(username);
                    message = deactivated ? "User deactivated successfully!" : "Failed to deactivate user.";
                    break;
                case "delete":
                    boolean deleted = userService.deleteUser(username);
                    message = deleted ? "User deleted successfully!" : "Failed to delete user.";
                    break;
                case "reset":
                    boolean reset = userService.resetPassword(username);
                    message = reset ? "Password reset successfully!" : "Failed to reset password.";
                    break;
                default:
                    message = "Invalid action!";
                    break;
            }
        }

        // Set the result message as an attribute to display in the JSP
        request.setAttribute("message", message);

        // Forward the request back to the modify_users.jsp page to display the result
        request.getRequestDispatcher("/WEB-INF/pages/admin/modifydash.jsp").forward(request, response);
    }
}
