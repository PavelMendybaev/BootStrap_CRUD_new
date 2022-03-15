package ru.kata.spring.boot_security.demo.controller;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller

public class MainController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")

    public String getIndex(){
        return "index";
    }


    @GetMapping(value = "/login" )
    public String getLogin(){
        return "login";
    }

    @GetMapping(value = "/admin" )
    public String getAdmin(ModelMap model){
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        return "admin";
    }

    @GetMapping("/user")
    public String getUser( ModelMap model){
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        return "user";
    }



}
