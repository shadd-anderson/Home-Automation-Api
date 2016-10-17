package com.homeautomation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;

@Entity
public class User extends GenericEntity {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String[] roles;

    protected User() {
        super();
    }

    public User(String username, String password, String[] roles) {
        this();
        this.username = username;
        setPassword(password);
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public boolean hasRole(String role) {
        for(int i = 0; i<roles.length; i++) {
            if(roles[i].equals(role)) {
                return true;
            }
        }
        return false;
    }
}
