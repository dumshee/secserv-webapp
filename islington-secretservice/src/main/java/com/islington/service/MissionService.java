package com.islington.service;

import com.islington.model.Mission;
import com.islington.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionService {

    public List<Mission> getAllMissions() {
        List<Mission> missions = new ArrayList<>();

        String query = "SELECT * FROM mission ORDER BY start_date DESC";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                missions.add(extractMission(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return missions;
    }

    public List<Mission> searchMissions(String keyword) {
        List<Mission> missions = new ArrayList<>();

        String query = "SELECT * FROM mission WHERE mission_name LIKE ? ORDER BY start_date DESC";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    missions.add(extractMission(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return missions;
    }

    private Mission extractMission(ResultSet rs) throws SQLException {
        Mission mission = new Mission();
        mission.setMissionId(rs.getInt("mission_id"));
        mission.setMissionName(rs.getString("mission_name"));
        mission.setMissionDescription(rs.getString("mission_description"));
        mission.setStartDate(rs.getDate("start_date"));
        mission.setEndDate(rs.getDate("end_date"));
        mission.setStatus(rs.getString("status"));
        return mission;
    }
}
