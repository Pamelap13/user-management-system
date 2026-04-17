package org.example.repository;

import org.example.model.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user );
    Optional<User> findById(Long id);
    boolean existsById(Long id);
}
