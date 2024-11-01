package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a UserNotFoundException with the specified user ID.
     *
     * @param userId the ID of the user that was not found
     */
    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
    }

    /**
     * Constructs a UserNotFoundException with the specified email address.
     *
     * @param email the email of the user that was not found
     */
    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}
