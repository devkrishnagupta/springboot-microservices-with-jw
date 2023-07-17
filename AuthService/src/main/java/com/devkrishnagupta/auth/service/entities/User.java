package com.devkrishnagupta.auth.service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "about")
    private String about;

//    @Transient
//    private List<Rating> ratings = new ArrayList<>();
}
