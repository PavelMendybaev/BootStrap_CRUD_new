package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

public interface UserServiveIterface {






    public void save(User user);

    public List<User> users();

    public User getUserById(Long id);

    public void deleteById(Long id);
}
