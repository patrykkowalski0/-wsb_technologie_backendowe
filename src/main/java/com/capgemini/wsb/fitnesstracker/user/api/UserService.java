package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user entity to create.
     * @return The created {@link User} entity.
     */
    User createUser(User user);

    /**
     * Updates an existing user by ID.
     *
     * @param id          ID of the user to update.
     * @param userDetails User details to update.
     * @return The updated {@link User} entity.
     */
    User updateUser(Long id, User userDetails);

    /**
     * Deletes a user by ID.
     *
     * @param id ID of the user to delete.
     */
    void deleteUser(Long id);

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    List<User> findAllUsers();

    /**
     * Retrieves a user by ID.
     *
     * @param userId The ID of the user.
     * @return An Optional containing the user if found.
     */
    Optional<User> getUser(Long userId);
    Optional<User> getUserByEmail(String email);
}
