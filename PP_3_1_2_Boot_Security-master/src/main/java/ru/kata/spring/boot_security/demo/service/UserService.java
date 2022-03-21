package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;


@Service
public class UserService implements UserServiveIterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }
    @Override
    public List<User> users(){
        return userRepository.users();
    }
    @Override
    public User getUserById(Long id){
        return userRepository.getUserById(id);
    }
    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
