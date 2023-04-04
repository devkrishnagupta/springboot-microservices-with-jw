package com.devkrishnagupta.user.service.services;

import com.devkrishnagupta.user.service.entity.User;

import java.util.List;

public interface UserService {
    //user operations
    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get single user
    User getUser(String userId);
}
