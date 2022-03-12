package ru.kata.spring.boot_security.demo.model;

public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    private final String Permission;


    Permission(String permission){
        this.Permission = permission;
    }

    public String getPermission() {
        return Permission;
    }
}
