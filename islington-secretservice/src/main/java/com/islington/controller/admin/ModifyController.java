package com.islington.controller.admin;

import com.islington.model.AgentModel;
import com.islington.service.UserService;
import com.islington.utils.CookieUtil;
import com.islington.utils.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet implementation for handling user modification-related HTTP requests.
 * 
 * This servlet manages the actions of deactivating, deleting, or resetting a user's
 * password. It communicates with the UserService to perform the necessary actions
 * and forwards results to the modifydash.jsp page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/modifydash" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 15
)
public class ModifyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    public ModifyController() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AgentModel> agentList = userService.getAllAgents();
        request.setAttribute("agentList", agentList);
        request.getRequestDispatcher("/WEB-INF/pages/admin/modifydash.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String log = request.getParameter("log");

         if ("logout".equals(log)) {
             request.getSession().invalidate();
             CookieUtil.deleteCookie(response, "role");
             response.sendRedirect(request.getContextPath() + "/login");
             return;
         }
         
        String action = request.getParameter("action");
        String message = "";

        try {
            if ("create".equals(action) || "update".equals(action)) {
                AgentModel agent = new AgentModel();
                
                // Set the agent details from the form
                if (request.getParameter("agentId") != null && !request.getParameter("agentId").isEmpty()) {
                    agent.setAgentId(Integer.parseInt(request.getParameter("agentId")));
                }
                agent.setFirstName(request.getParameter("firstName"));
                agent.setLastName(request.getParameter("lastName"));
                agent.setCodename(request.getParameter("codename"));
                agent.setDob(LocalDate.parse(request.getParameter("dob")));
                agent.setGender(request.getParameter("gender"));
                agent.setEmail(request.getParameter("email"));
                agent.setNumber(request.getParameter("number"));
                
                // Encrypt the password
                String encryptedPassword = PasswordUtil.encrypt(agent.getEmail(), request.getParameter("password"));
                agent.setPassword(encryptedPassword);

                if ("create".equals(action)) {
                    userService.addAgent(agent);
                    message = "Agent created successfully!";
                } else {
                    userService.updateAgent(agent);
                    message = "Agent updated successfully!";
                }

            } else if ("delete".equals(action)) {
                int agentId = Integer.parseInt(request.getParameter("agentId"));
                userService.deleteAgent(agentId);
                message = "Agent deleted successfully!";
            } else {
                message = "Invalid action!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred: " + e.getMessage();
        }

        // Fetch the updated agent list and set it to the request
        List<AgentModel> agentList = userService.getAllAgents();
        request.setAttribute("agentList", agentList);
        request.setAttribute("message", message);

        // Forward to the modifydash.jsp page with the message and updated agent list
        request.getRequestDispatcher("/WEB-INF/pages/admin/modifydash.jsp").forward(request, response);
        
    }
}
