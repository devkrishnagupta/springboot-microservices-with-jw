package com.devkrishnagupta.hotel.service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaff(){
        List<String> list = Arrays.asList("Ram","Sita", "Bharat", "Krishna");
        return ResponseEntity.ok(list);
    }
}
