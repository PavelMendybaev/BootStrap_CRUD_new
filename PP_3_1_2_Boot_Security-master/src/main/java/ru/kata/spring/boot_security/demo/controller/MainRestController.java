package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;


@RestController
@RequestMapping("/api")
public class MainRestController {



    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MainRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        System.out.println(user.getRoles());

        userService.save(user);

        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }

    @PostMapping("/users/edit/{id}")
    public  ResponseEntity<List<User>> editUser(@RequestBody User user , @PathVariable Long id) {
        User editUser = userService.getUserById(id);


        editUser.setName(user.getName());
        editUser.setPassword(passwordEncoder.encode(user.getPassword()));
        editUser.setRoles(user.getRoles());
        userService.save(user);

        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }


    @PostMapping("/users/del/{id}")
    public ResponseEntity<List<User>> delUser(@PathVariable Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>( userService.users() , HttpStatus.OK);
    }
    @GetMapping("/roles")
    ResponseEntity<List<Role>>getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @PostMapping("/roles")
    void AddRoles(@PathVariable Role role){

    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.OK);
    }
}
