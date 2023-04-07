package com.devkrishnagupta.hotel.service.repositories;

import com.devkrishnagupta.hotel.service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
