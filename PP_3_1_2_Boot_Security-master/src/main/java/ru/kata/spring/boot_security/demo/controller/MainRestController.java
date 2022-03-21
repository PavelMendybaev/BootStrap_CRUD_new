package ru.kata.spring.boot_security.demo.controller;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;


import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;


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
    public ResponseEntity<List<User>> showUsers(){
        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id){
        return new ResponseEntity<>( userService.getUserById(id) , HttpStatus.OK);
    }


    @PostMapping("/users")
    public  ResponseEntity<List<User>> createUser(@RequestBody User user) {


        userService.save(user);

        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }

    @PostMapping("/users/edit/{id}")
    public  ResponseEntity<List<User>> editUser(@RequestBody User user , @PathVariable Long id) {


        User editUser = userService.getUserById(id);


        editUser.setName(user.getName());
        editUser.setPassword(passwordEncoder.encode(user.getPassword()));
        editUser.setRole(user.getRole());
        userService.save(editUser);

        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }


    @PostMapping("/users/del/{id}")
    public ResponseEntity<List<User>> delUser(@PathVariable Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }



}
