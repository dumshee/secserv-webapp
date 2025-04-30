package com.islington.service;

import com.islington.model.AgentModel;
import com.islington.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateService {

    private Connection dbConn;

    public UpdateService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean updateAgentProfile(AgentModel agent) {
        String sql = "UPDATE agent SET email = ?, number = ? WHERE codename = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setString(1, agent.getEmail());
            stmt.setString(2, agent.getNumber());
            stmt.setString(3, agent.getCodename());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
