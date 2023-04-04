package com.devkrishnagupta.user.service.repositry;

import com.devkrishnagupta.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
