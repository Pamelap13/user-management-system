package org.example;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.InMemoryUserRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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


        userService.deleteUser(3L);
        System.out.println("\nAfter delete");
        userService.getAllUsers().forEach(
                u-> System.out.println(u.getName())
        );
        userService.findByEmail("luis@email.com")
                .ifPresent(u-> System.out.println("\nfound: " + u.getName()));

        User user = new User(1L, "Pamela", "pamela@email.com", true);
        Predicate<User> isActiveUser = u -> u.isActive();
        Function<User,String> getEmail = u -> u.getEmail();
        Consumer<User> printName = u -> System.out.println(u.getName());
        System.out.println(isActiveUser.test(user));
        System.out.println(getEmail.apply(user));
        printName.accept(user);
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