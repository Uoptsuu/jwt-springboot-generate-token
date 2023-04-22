package com.spring.jwt.service;

import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;

public interface UserService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void addToUser(String username, String roleName);
}
