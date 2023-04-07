package com.devkrishnagupta.rating.ratingservice.services;

import com.devkrishnagupta.rating.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);
    //getAllRatings
    List<Rating> getAllRatings();
    //get All by UserId
    List<Rating> getRatingsByUserId(String userId);
    //get All by hotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
