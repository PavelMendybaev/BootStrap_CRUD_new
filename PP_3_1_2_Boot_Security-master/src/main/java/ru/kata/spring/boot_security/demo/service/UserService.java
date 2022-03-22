package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;


@Service
public class UserService implements UserServiveIterface , UserDetailsService {

    private final UserDAO userDAO;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }
    @Override
    public List<User> users(){
        return userDAO.getAllUsers();
    }
    @Override
    public User getUserById(Long id){
        return userDAO.getUser(id);
    }
    @Override
    public void deleteById(Long id){
        userDAO.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return userDAO.getUserByUsername(username);

    }
}
