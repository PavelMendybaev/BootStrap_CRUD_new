package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    void saveRole(Role role);

    Role getRole(Long id);

    Role getRoleByName(String name);

    void deleteRole(Long id);
}
