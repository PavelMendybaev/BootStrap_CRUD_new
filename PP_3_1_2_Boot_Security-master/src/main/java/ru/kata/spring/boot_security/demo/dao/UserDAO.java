package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();



    void updateUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

    User getUserByUsername(String name);


}
