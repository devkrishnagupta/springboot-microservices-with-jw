package com.devkrishnagupta.user.service.external.service;

import com.devkrishnagupta.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    @GetMapping("api/ratings/user/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable("userId") String userId);

    @PostMapping("api/ratings")
    public Rating createRating(Rating values);

    @PutMapping("api/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, @RequestBody Rating rating);

    @DeleteMapping("api/ratings/{ratingId}")
    public void deletingRating(@PathVariable String ratingId);
}
