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

    @GetMapping(value = "/admin/add")
    public String getAdd(ModelMap model){
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        return "admin/admin_add";
    }


    @PostMapping(value = "/admin/add")
    public String PostAdd(@RequestParam("username") String name ,
                            @RequestParam("password") String password ,
                            @RequestParam("role") String role ,
                            ModelMap model ) {
        userService.save(new User(name , passwordEncoder.encode(password) ,Role.valueOf(role)));

        List<User> users = userService.users();

        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        model.addAttribute("users", users);
        return "admin";
    }



    @GetMapping(value = "/login" )
    public String getLogin(){


        return "login";
    }


    @GetMapping(value = "/admin" )
    public String getAdmin(ModelMap model){

        List<User> users = userService.users();
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("users", users);
        model.addAttribute("myUser" , myUser);


        return "admin";
    }


    @GetMapping("/user/users")
    public String showUser( ModelMap model){
        List<User> users = userService.users();
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        model.addAttribute("users", users);
        return "users";

    }






    @RequestMapping(value = "/user/{id}" , method = RequestMethod.POST)
    public String getUser(@PathVariable("id") Long id ,@RequestParam("new_username") String new_name , ModelMap model){

        User user = userService.getUserById(id);
        user.setName(new_name);
        userService.save(user);
        model.addAttribute("user" , user);
        return "user";

    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long id , ModelMap model){

        userService.deleteById(id);

        return "delete";
    }

    @GetMapping(value = "/admin/edit/{id}" )
    public String getEdit(@PathVariable("id") Long id,ModelMap model){
        List<User> users = userService.users();
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        model.addAttribute("users", users);
        model.addAttribute("id_user" , id);
        return "admin/edit_user";
    }

    @PostMapping(value = "/admin/edit/{id}")
    public String Postedit(@PathVariable("id") Long id,
                           @RequestParam("username") String name ,
                           @RequestParam("password") String password ,
                           @RequestParam("role") String role ,
                          ModelMap model ) {


        User user = userService.getUserById(id);
        user.setName(name);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));
        userService.save(user);


        List<User> users = userService.users();

        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        model.addAttribute("users", users);

        return "admin";
    }


    @GetMapping(value = "/admin/delete/{id}" )
    public String getDelete(@PathVariable("id") Long id,ModelMap model){
        userService.deleteById(id);
        return "delete";
    }

    @GetMapping("/user")
    public String getUser( ModelMap model){
        List<User> users = userService.users();
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        model.addAttribute("users", users);
        return "user";

    }

    @GetMapping("/test/user")
    public String testUser( ModelMap model){
        List<User> users = userService.users();
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myUser" , myUser);
        model.addAttribute("users", users);
        return "rest/restUsers";

    }



}
