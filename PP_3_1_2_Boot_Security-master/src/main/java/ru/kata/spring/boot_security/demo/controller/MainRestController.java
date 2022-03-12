package ru.kata.spring.boot_security.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainRestController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MainRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users" )
    public List<User> showUsers(){
        return userService.users();
    }
    @GetMapping("/users/{id}")
    public User showUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public List<User> createUser(@RequestBody User user){
        userService.save(user);
        return userService.users();
    }
}
