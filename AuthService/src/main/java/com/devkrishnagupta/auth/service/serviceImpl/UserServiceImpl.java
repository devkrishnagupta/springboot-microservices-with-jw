package com.devkrishnagupta.auth.service.serviceImpl;

import com.devkrishnagupta.auth.service.entities.User;
import com.devkrishnagupta.auth.service.repositories.UserRepository;
import com.devkrishnagupta.auth.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
