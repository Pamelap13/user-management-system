package org.example;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.service.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
    try{
        User user1 = new User(1L, "Pamela", "pame@email.com", true);
        User user2 = new User(1L, "Ana", "ana@email.com", true);
        userService.addUser(user1);
        userService.addUser(user2);
    }catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
    }

        try{
            userService.findUserById(1L)
                    .ifPresent(user -> System.out.println("Found: " + user.getName()));
            userService.findUserOrThrow(3L);
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}