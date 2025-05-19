package com.islington.service;

import com.islington.config.DbConfig;
import com.islington.model.AgentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public DashboardService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public List<AgentModel> getAllAgents() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT agent_id, first_name, last_name, codename, email FROM agent";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<AgentModel> agentList = new ArrayList<>();

            while (result.next()) {
                agentList.add(new AgentModel(
                        result.getInt("agent_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("codename"),
                        result.getString("email")
                ));
            }

            return agentList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalAgents() {
        if (isConnectionError) {
            return 0;
        }

        String query = "SELECT COUNT(*) AS total FROM agent";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
