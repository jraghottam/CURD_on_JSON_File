package com.example.Json.controller;

import com.example.Json.model.ResponseModel;
import com.example.Json.model.User;
import com.example.Json.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/adduser")
    public ResponseModel addUser(@RequestBody User user) throws IOException {
        return userService.addUser(user);
    }

    @PutMapping("/updateuser")
    public ResponseModel updateuser(@RequestBody User user) throws IOException {
        return userService.updateuser(user);
    }

    @DeleteMapping("/deleteuser/{userid}")
    public ResponseModel deleteuser(@PathVariable String userid ){
        return userService.deleteuser(userid);
    }

    @GetMapping("/getalluser")
    public ResponseModel getalluser(){
        return userService.getalluser();
    }
}
