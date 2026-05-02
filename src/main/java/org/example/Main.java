package org.example;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.InMemoryUserRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepository repository = new InMemoryUserRepository();
        UserService userService = new UserService(repository);
    try{
        userService.addUser(new User(1L, "Pamela", "pamela@email.com", true));
        userService.addUser(new User(2L, "Ana", "ana@email.com", false));
        userService.addUser(new User(3L, "Luis", "luis@email.com", true));

        System.out.println("All users");
        userService.getAllUsers().forEach(
                u -> System.out.println(u.getName())
        );

        System.out.println("\nActive users");
        userService.getActiveUsers().forEach(
                u-> System.out.println(u.getName())
        );
        userService.findByEmail("luis@email.com")
                .ifPresent(u-> System.out.println("\nfound: " + u.getName()));
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