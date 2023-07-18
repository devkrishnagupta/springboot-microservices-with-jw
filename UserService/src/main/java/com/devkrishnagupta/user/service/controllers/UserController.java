package com.devkrishnagupta.user.service.controllers;

import com.devkrishnagupta.user.service.entity.Hotel;
import com.devkrishnagupta.user.service.entity.Rating;
import com.devkrishnagupta.user.service.entity.User;
import com.devkrishnagupta.user.service.external.service.HotelService;
import com.devkrishnagupta.user.service.external.service.RatingService;
import com.devkrishnagupta.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount=1;
    //single user
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUserWithCircuitBreaker(@PathVariable String userId){
        logger.info("Retry count: {}", retryCount++);
        logger.info("Get Single User Handler: UserController: userId-> "+userId);
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because Hotel service is down : "+ex.getMessage());
        User user =  User.builder()
               .userId("testId")
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some services is down")
                .build();
        return new ResponseEntity<>(user, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    @GetMapping("/{userId}/first")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        //get user from database with the help of user repository
        User user = userService.getUser(userId);
        //fetch the rating of the above user from RATING-SERVICE
        //http://localhost:8083/api/ratings/user/63277621-4488-4457-afd5-0017164bbac7
//        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/api/ratings/user/"+userId, Rating[].class);
//        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratings = ratingService.getRatingsByUserId(userId);

        logger.info("Ratings with user id {} is {}", userId, ratings);
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //API call to hotel service to get the hotel
            http://localhost:8082/api/hotels/34fb1d0a-8318-472a-a0c8-a01ec15d2dd8
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/api/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
            //set the hotel to rating
//            rating.setHotel(hotel);
//            rating.setHotel(restTemplate.getForEntity("http://HOTEL-SERVICE/api/hotels/" + rating.getHotelId(), Hotel.class).getBody());
            rating.setHotel(hotelService.getHotel(rating.getHotelId()));
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return ResponseEntity.ok(user);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        logger.info("GETTING ALL USERS INFO:- API");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
