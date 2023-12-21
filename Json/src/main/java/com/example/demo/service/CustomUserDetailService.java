package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//
//       users user = userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User Not Found"));
        
        users user = userRepository.findByEmail(username);
        return user;
    }
}
