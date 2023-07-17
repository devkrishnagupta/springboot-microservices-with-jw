package com.devkrishnagupta.auth.service.serviceImpl;

import com.devkrishnagupta.auth.service.entities.User;
import com.devkrishnagupta.auth.service.repositories.UserRepository;
import com.devkrishnagupta.auth.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUserId(String userId) {
        Optional<User> user=userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
