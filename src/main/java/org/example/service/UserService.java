package org.example.service;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final Map<Long, User> users = new HashMap<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser (User user){

        if (userRepository.existsById(user.getId())){
            throw new IllegalArgumentException(
                    "User with id "+ user.getId() + " already exists"
            );
        }
        userRepository.save(user);
    }

    public Optional<User> findUserById( Long id){
        return userRepository.findById(id);
    }
    public User findUserOrThrow(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
