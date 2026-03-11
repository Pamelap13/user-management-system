package org.example.service;

import org.example.exception.UserNotFoundException;
import org.example.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public void addUser (User user){

        if (users.containsKey(user.getId())){
            throw new IllegalArgumentException(
                    "User with id "+ user.getId() + " already exists"
            );
        }
        users.put(user.getId(), user);
    }

    public Optional<User> findUserById( Long id){
        return Optional.ofNullable(users.get(id));
    }
    public User findUserOrThrow(Long id){
        return Optional.ofNullable(users.get(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
