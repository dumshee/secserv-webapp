package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.model.AgentModel;
import com.islington.service.UpdateService;
import com.islington.utils.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/MyProfile" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class MyProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyProfileController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/MyProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("doPost triggered");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String number = request.getParameter("number");

        // Get codename from session
        String codename = (String) SessionUtil.getAttribute(request, "username");

        System.out.println("Received: " + codename + ", " + email + ", " + number);

        if (codename == null) {
            System.out.println("No codename found in session");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        AgentModel agent = new AgentModel(0, null, null, codename, null, null, email, number, null, 0);
        agent.setCodename(codename);
        agent.setEmail(email);
        agent.setNumber(number);

        UpdateService service = new UpdateService();
        boolean updated = service.updateAgentProfile(agent);
        System.out.println("Update status: " + updated);

        if (updated) {
            SessionUtil.setAttribute(request, "email", email);
            SessionUtil.setAttribute(request, "number", number);
            System.out.println("Update successful.");
            response.sendRedirect(request.getContextPath() + "/MyProfile");
        } else {
            System.out.println("Update failed.");
            request.setAttribute("error", "Update failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/MyProfile.jsp").forward(request, response);
        }
    }
}
