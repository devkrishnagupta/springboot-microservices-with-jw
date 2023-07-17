package com.devkrishnagupta.auth.service.repositories;

import com.devkrishnagupta.auth.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByName(String username);

    Optional<User> findByEmail(String username);
}
