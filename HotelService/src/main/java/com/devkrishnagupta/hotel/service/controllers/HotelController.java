package com.devkrishnagupta.hotel.service.controllers;

import com.devkrishnagupta.hotel.service.entity.Hotel;
import com.devkrishnagupta.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    //get Single
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getAHotel(@PathVariable String hotelId){
        return ResponseEntity.ok(hotelService.getAHotel(hotelId));
    }

    //get ALL
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getALlHotels(){
        return ResponseEntity.ok(hotelService.getAll());
    }
}
