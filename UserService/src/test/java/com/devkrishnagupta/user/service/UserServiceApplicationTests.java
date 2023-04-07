package com.devkrishnagupta.user.service;

import com.devkrishnagupta.user.service.entity.Rating;
import com.devkrishnagupta.user.service.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
//		Rating rating=Rating
//				.builder()
//				.rating(10)
//				.userId("bee3c237-44c8-4ba8-9a18-79301969f0d1")
//				.hotelId("34fb1d0a-8318-472a-a0c8-a01ec15d2dd8")
//				.feedback("This is created using feign client")
//				.build();
//		ratingService.createRating(rating);
		System.out.println("New Rating created using feign client");
	}

}
