package com.devkrishnagupta.hotel.service.services;

import com.devkrishnagupta.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel create(Hotel hotel);

    //getall
    List<Hotel> getAll();

    //get Single
    Hotel getAHotel(String hotelId);
}
