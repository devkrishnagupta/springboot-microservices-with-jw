package com.devkrishnagupta.user.service.services.impl;

import com.devkrishnagupta.user.service.entity.User;
import com.devkrishnagupta.user.service.exception.ResourceNotFoundException;
import com.devkrishnagupta.user.service.repositry.UserRepository;
import com.devkrishnagupta.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server 1"));
    }
}
