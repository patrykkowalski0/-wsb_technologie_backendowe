package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link User} entities, extending {@link JpaRepository}.
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches for a user by exact email match.
     *
     * @param email Email address of the user.
     * @return Optional containing found user or empty if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Searches for users whose email contains the specified substring, ignoring case.
     *
     * @param email Email substring to search for.
     * @return List of users matching the criteria.
     */
    List<User> findByEmailContainingIgnoreCase(String email);
}
