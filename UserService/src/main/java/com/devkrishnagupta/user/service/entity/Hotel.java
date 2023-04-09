package com.devkrishnagupta.user.service.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
