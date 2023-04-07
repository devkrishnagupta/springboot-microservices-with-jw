package com.devkrishnagupta.user.service.external.service;

import com.devkrishnagupta.user.service.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("api/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
