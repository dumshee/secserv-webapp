package com.islington.service;

public class UserService {

    // Mocked method to deactivate a user
    public boolean deactivateUser(String username) {
        // Perform the necessary operation (e.g., update user status in DB)
        System.out.println("Deactivating user: " + username);
        return true; // Simulate success
    }

    // Mocked method to delete a user
    public boolean deleteUser(String username) {
        // Perform the necessary operation (e.g., delete user from DB)
        System.out.println("Deleting user: " + username);
        return true; // Simulate success
    }

    // Mocked method to reset a user's password
    public boolean resetPassword(String username) {
        // Perform the necessary operation (e.g., reset password)
        System.out.println("Resetting password for user: " + username);
        return true; // Simulate success
    }
}
