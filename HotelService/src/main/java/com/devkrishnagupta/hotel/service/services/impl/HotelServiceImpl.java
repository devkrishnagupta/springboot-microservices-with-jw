package com.devkrishnagupta.hotel.service.services.impl;

import com.devkrishnagupta.hotel.service.entity.Hotel;
import com.devkrishnagupta.hotel.service.exception.ResourceNotFoundException;
import com.devkrishnagupta.hotel.service.repositories.HotelRepository;
import com.devkrishnagupta.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @Override
    public Hotel getAHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found"));
    }
}
