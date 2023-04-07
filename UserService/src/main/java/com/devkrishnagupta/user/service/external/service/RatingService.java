package com.devkrishnagupta.user.service.external.service;

import com.devkrishnagupta.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("api/ratings/user/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable("userId") String userId);
}
