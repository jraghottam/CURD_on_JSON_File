package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    List<users> store = new ArrayList<>();
//
//    public UserService() {
//        store.add(new users(UUID.randomUUID().toString(),"testname","testname@gmail"));
//        store.add(new users(UUID.randomUUID().toString(),"testname1","testname1@gmail"));
//        store.add(new users(UUID.randomUUID().toString(),"testname2","testname2@gmail"));
//    }
//
    public List<users> getusersList() {
        return userRepository.findAll();
    }


    public users createUser(users user){
//        user.setUserid(Integer.parseInt(UUID.randomUUID().toString()));
        String pass= passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        return userRepository.save(user);

    }
public users findByUsername(String username) {
    return userRepository.findByEmail(username);
}
}
