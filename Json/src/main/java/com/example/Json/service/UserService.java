package com.example.Json.service;

import com.example.Json.model.ResponseModel;
import com.example.Json.model.User;

import java.io.IOException;

public interface UserService {
    ResponseModel addUser(User user) throws IOException;

    ResponseModel getalluser();

    ResponseModel updateuser(User user) throws IOException;

    ResponseModel deleteuser(String userid) throws IOException;
}
