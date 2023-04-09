package com.devkrishnagupta.user.service.services.impl;

import com.devkrishnagupta.user.service.entity.Hotel;
import com.devkrishnagupta.user.service.entity.Rating;
import com.devkrishnagupta.user.service.entity.User;
import com.devkrishnagupta.user.service.exception.ResourceNotFoundException;
import com.devkrishnagupta.user.service.external.service.HotelService;
import com.devkrishnagupta.user.service.external.service.RatingService;
import com.devkrishnagupta.user.service.repositry.UserRepository;
import com.devkrishnagupta.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server 1"));
        List<Rating> ratings = ratingService.getRatingsByUserId(userId);
        List<Rating> ratingList = ratings.stream().map((rating) ->{
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            logger.info("FROM USER SERVICE IMPL : "+hotel.toString());
            return rating;
        }).collect(Collectors.toList());
        logger.info(".................");
        user.setRatings(ratingList);
        return user;
    }
}
