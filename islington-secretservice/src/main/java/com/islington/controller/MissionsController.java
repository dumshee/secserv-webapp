package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.islington.model.Mission;
import com.islington.service.MissionService;
import com.islington.utils.SessionUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/missions" })
public class MissionsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MissionsController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) SessionUtil.getAttribute(request, "username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String search = request.getParameter("search");
        MissionService missionService = new MissionService();
        List<Mission> missions;

        if (search != null && !search.trim().isEmpty()) {
            missions = missionService.searchMissions(search.trim());
            request.setAttribute("searchPerformed", true);
            request.setAttribute("searchTerm", search.trim());
        } else {
            missions = missionService.getAllMissions();
            request.setAttribute("searchPerformed", false);
            request.setAttribute("searchTerm", "");
        }

        request.setAttribute("missions", missions);
        request.getRequestDispatcher("/WEB-INF/pages/missions.jsp").forward(request, response);
    }
}
