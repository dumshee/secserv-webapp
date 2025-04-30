package com.islington.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.AgentModel;

/**
 * RegisterService handles the registration of new agents. It manages database
 * interactions for agent registration.
 */
public class RegisterService {

	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Registers a new agent in the database.
	 *
	 * @param agentModel the agent details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addAgent(AgentModel agentModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		// Check if codename is null or empty
		if (agentModel.getCodename() == null || agentModel.getCodename().isEmpty()) {
			System.err.println("Codename cannot be null or empty.");
			return false;
		}

		String checkCodenameQuery = "SELECT * FROM agent WHERE codename = ?";
		String programQuery = "SELECT program_id FROM program WHERE program_name = ?";
		String insertQuery = "INSERT INTO agent (codename, first_name, last_name, dob, gender, email, number, password, program_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement checkCodenameStmt = dbConn.prepareStatement(checkCodenameQuery);
				PreparedStatement programStmt = dbConn.prepareStatement(programQuery);
				PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Check if codename already exists
			checkCodenameStmt.setString(1, agentModel.getCodename());
			ResultSet checkResult = checkCodenameStmt.executeQuery();

			if (checkResult.next()) {
				// Codename already exists, handle the error
				System.err.println("Codename already exists. Please choose another one.");
				return false;
			}

			// Fetch program ID
			programStmt.setInt(1, agentModel.getProgramId());
			ResultSet result = programStmt.executeQuery();
			int programId = result.next() ? result.getInt("program_id") : 1;

			// Insert agent details
			insertStmt.setString(1, agentModel.getCodename());
			insertStmt.setString(2, agentModel.getFirstName());
			insertStmt.setString(3, agentModel.getLastName());
			insertStmt.setDate(4, Date.valueOf(agentModel.getDob()));
			insertStmt.setString(5, agentModel.getGender());
			insertStmt.setString(6, agentModel.getEmail());
			insertStmt.setString(7, agentModel.getNumber());
			insertStmt.setString(8, agentModel.getPassword());
			insertStmt.setInt(9, programId);

			int rowsAffected = insertStmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Agent registered successfully.");
				return true;
			} else {
				System.err.println("Failed to register agent.");
				return false;
			}

		} catch (SQLException e) {
			System.err.println("Error during agent registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
