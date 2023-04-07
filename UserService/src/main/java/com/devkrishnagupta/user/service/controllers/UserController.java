package com.devkrishnagupta.user.service.controllers;

import com.devkrishnagupta.user.service.entity.Rating;
import com.devkrishnagupta.user.service.entity.User;
import com.devkrishnagupta.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        //get user from database with the help of user repository
        User user = userService.getUser(userId);
        //fetch the rating of the above user from RATING-SERVICE
        //http://localhost:8083/api/ratings/user/63277621-4488-4457-afd5-0017164bbac7
        ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8083/api/ratings/user/"+userId, ArrayList.class);
        logger.info("Ratings with user id {} is {}", userId, ratings);
        user.setRatings(ratings);
        return ResponseEntity.ok(user);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
