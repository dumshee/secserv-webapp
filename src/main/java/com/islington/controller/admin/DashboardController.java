package com.islington.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.islington.model.AgentModel;
import com.islington.service.DashboardService;

/**
 * Servlet for handling the admin dashboard.
 * It retrieves information about agents and forwards it to the dashboard JSP page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admindash" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
maxFileSize = 1024 * 1024 * 10,      // 10 MB
maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of DashboardService for handling business logic
    private DashboardService dashboardService;

    public DashboardController() {
        super();
        this.dashboardService = new DashboardService();  // Initialize the service
    }

    /**
     * Handles GET requests to the dashboard page.
     * Retrieves statistics about agents and forwards the request to the dashboard JSP.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve list of agents and their stats
        List<AgentModel> agentList = dashboardService.getAllAgents();
        int totalAgents = agentList.size();  // Total number of agents
        
        // Optionally, you could include filters or statistics like active agents, etc.
        
        // Set attributes to be accessed in the JSP
        request.setAttribute("totalAgents", totalAgents);
        request.setAttribute("agentList", agentList);

        // Forward to the dashboard JSP page
        request.getRequestDispatcher("/WEB-INF/pages/admin/admindash.jsp").forward(request, response);
    }
}
