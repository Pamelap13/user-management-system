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
        if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!user.getEmail().contains("@")){
            throw new IllegalArgumentException("Invalid email format");
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
    public void updateEmail(Long id, String newEmail){
        User user = findUserOrThrow(id);
        if (!user.isActive()){
            throw new IllegalStateException("Cannot update email of inactive user");
        }
        if (newEmail == null || newEmail.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!newEmail.contains("@")){
            throw new IllegalArgumentException("Invalid email format");
        }
        user.changeEmail(newEmail);
    }
}
