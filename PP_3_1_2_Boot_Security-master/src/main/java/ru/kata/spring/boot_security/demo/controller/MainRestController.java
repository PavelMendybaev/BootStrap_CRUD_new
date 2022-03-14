package ru.kata.spring.boot_security.demo.controller;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;


import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

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
    public List<User> showUsers(){
        return userService.users();
    }

    @GetMapping("/users/{id}")
    public User showUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }


    @PostMapping("/users")
    public List<User> createUser(@RequestBody String strUser) {

        ObjectMapper mapper = new ObjectMapper();
        try{
            User user = new User();

            JsonNode jsonNode = mapper.readTree(strUser);
            user.setName(jsonNode.get("login").asText());
            user.setPassword(passwordEncoder.encode(jsonNode.get("password").asText()));
            user.setRole(Role.valueOf(jsonNode.get("role").asText()));
            userService.save(user);

        }catch (Exception e){
            System.out.println("не вверный формат");
        }

        return userService.users();
    }

    @PostMapping("/users/edit/{id}")
    public List<User> editUser(@RequestBody String strUser, @PathVariable Long id) {


        ObjectMapper mapper = new ObjectMapper();
        try{

            User user = userService.getUserById(id);
            JsonNode jsonNode = mapper.readTree(strUser);

            user.setName(jsonNode.get("login").asText());
            user.setPassword(passwordEncoder.encode(jsonNode.get("password").asText()));
            user.setRole(Role.valueOf(jsonNode.get("role").asText()));
            userService.save(user);

        }catch (Exception e){
            System.out.println("не вверный формат");
        }
        return userService.users();
    }


    @PostMapping("/users/del/{id}")
    public List<User> delUser(@PathVariable Long id) {
        userService.deleteById(id);
        return userService.users();
    }



}
