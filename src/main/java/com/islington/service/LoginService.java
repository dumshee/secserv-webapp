package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.AgentModel;
import com.islington.utils.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection. Sets the connection error
     * flag if the connection fails.
     */
    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
            System.out.println("Database connection successful!");  // Log successful connection
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
            System.out.println("Database connection failed!");  // Log failure
        }
    }

    /**
     * Validates the user credentials against the database records.
     *
     * @param agentModel the AgentModel object containing user credentials
     * @return true if the user credentials are valid, false otherwise; null if a
     *         connection error occurs
     */
    public Boolean loginUser(AgentModel agentModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT codename, password FROM agent WHERE codename = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, agentModel.getCodename());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                // Log the results from the database
                System.out.println("Found user: " + result.getString("codename"));

                return validatePassword(result, agentModel);
            } else {
                System.out.println("No user found in the database for codename: " + agentModel.getCodename());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return false;
    }


    /**
     * Validates the password retrieved from the database.
     * @param result       the ResultSet containing the username and password from
     *                     the database
     * @param agentModel   the AgentModel object containing user credentials
     * @return true if the passwords match, false otherwise
     * @throws SQLException if a database access error occurs
     */
    private boolean validatePassword(ResultSet result, AgentModel agentModel) throws SQLException {
        String dbcodename = result.getString("codename");
        String dbPassword = result.getString("password");

        // Log the credentials fetched from DB
        System.out.println("Database Codename: " + dbcodename);
        System.out.println("Database Encrypted Password: " + dbPassword);

        // Perform decryption and log the result
        String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbcodename);
        System.out.println("Decrypted Password: " + decryptedPassword);

        if (decryptedPassword != null && decryptedPassword.equals(agentModel.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

}
