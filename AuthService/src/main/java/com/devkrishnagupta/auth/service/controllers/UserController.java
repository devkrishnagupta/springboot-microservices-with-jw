package com.devkrishnagupta.auth.service.controllers;

import com.devkrishnagupta.auth.service.config.JwtTokenProvider;
import com.devkrishnagupta.auth.service.entities.User;
import com.devkrishnagupta.auth.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable(name = "userId") String userId){
        return userService.findByUserId(userId);
    }

    @PostMapping("login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()){
            return tokenProvider.generateToken(user.getEmail());
        }else{
            throw new RuntimeException("Invalid Access");
        }
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return newUser;
    }
}
