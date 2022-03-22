package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private final EntityManager em;

    public RoleDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public void saveRole(Role role) {
        em.merge(role);
    }

    @Override
    public Role getRole(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> tq = em.createQuery("select r from Role as r where r.role=:param", Role.class);
        return tq.setParameter("param", name).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = em.getReference(Role.class, id);
        em.remove(role);
    }
}