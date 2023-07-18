package com.devkrishnagupta.auth.service.service;

import com.devkrishnagupta.auth.service.entities.User;

import java.util.List;

public interface UserService {
    User findByUserId(String userId);

    List<User> getAllUser();

    User saveUser(User user);
}
