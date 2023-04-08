package com.devkrishnagupta.rating.ratingservice.controllers;

import com.devkrishnagupta.rating.ratingservice.entities.Rating;
import com.devkrishnagupta.rating.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    //get all ratings by hotelId
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
    }

    //get all ratings userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId){
        ratingService.deleteRating(ratingId);
    }
}
