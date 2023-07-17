package com.devkrishnagupta.auth.service.config;

import com.devkrishnagupta.auth.service.entities.User;
import com.devkrishnagupta.auth.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential = userRepository.findByEmail(username);
//        if (credential.isPresent()){
//            throw new UsernameNotFoundException("user not found with name :" + username);
//        }
//        return new CustomUserDetails(credential.get());
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
