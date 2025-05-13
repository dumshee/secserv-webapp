package com.islington.service;

import com.islington.config.DbConfig;
import com.islington.model.AgentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<AgentModel> getAllAgents() {
        List<AgentModel> agents = new ArrayList<>();
        String sql = "SELECT * FROM agent";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AgentModel agent = new AgentModel();
                agent.setAgentId(rs.getInt("agent_id"));
                agent.setFirstName(rs.getString("first_name"));
                agent.setLastName(rs.getString("last_name"));
                agent.setCodename(rs.getString("codename"));
                agent.setDob(rs.getDate("dob").toLocalDate());
                agent.setGender(rs.getString("gender"));
                agent.setEmail(rs.getString("email"));
                agent.setNumber(rs.getString("number"));
                agent.setPassword(rs.getString("password"));
                agents.add(agent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agents;
    }

    public void addAgent(AgentModel agent) {
        String sql = "INSERT INTO agent (first_name, last_name, codename, dob, gender, email, number, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agent.getFirstName());
            stmt.setString(2, agent.getLastName());
            stmt.setString(3, agent.getCodename());
            stmt.setDate(4, Date.valueOf(agent.getDob()));
            stmt.setString(5, agent.getGender());
            stmt.setString(6, agent.getEmail());
            stmt.setString(7, agent.getNumber());
            stmt.setString(8, agent.getPassword());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAgent(AgentModel agent) {
        String sql = "UPDATE agent SET first_name=?, last_name=?, codename=?, dob=?, gender=?, email=?, number=?, password=? WHERE agent_id=?";

        try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agent.getFirstName());
            stmt.setString(2, agent.getLastName());
            stmt.setString(3, agent.getCodename());
            stmt.setDate(4, Date.valueOf(agent.getDob()));
            stmt.setString(5, agent.getGender());
            stmt.setString(6, agent.getEmail());
            stmt.setString(7, agent.getNumber());
            stmt.setString(8, agent.getPassword());
            stmt.setInt(9, agent.getAgentId());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAgent(int id) {
        String sql = "DELETE FROM agent WHERE agent_id=?";

        try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalAgents() {
        String sql = "SELECT COUNT(*) FROM agent";
        try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
