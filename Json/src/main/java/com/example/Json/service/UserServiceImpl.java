package com.example.Json.service;

import com.example.Json.model.ResponseModel;
import com.example.Json.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    @Override
    public ResponseModel addUser(User user) throws IOException {
        ResponseModel responseModel = new ResponseModel();
        List<User> userData = readJsonData();
        User userispresent = null;

        if(userData != null){
            userispresent = userData.parallelStream().filter(usr->usr.getEmail().equals(user.getEmail())).findAny().orElse(null);
        } else {
            userData = new ArrayList<>();
        }
        if(userispresent == null){
            userData.add(user);

            boolean status = writeJsonData(userData);


        }else {
            System.out.println("user alredey present");
        }
        return responseModel;
    }

    @Override
    public ResponseModel getalluser() {
        ResponseModel responseModel = new ResponseModel();

        List<User> userData = readJsonData();
        responseModel.setData(userData);
        return responseModel;
    }

    @Override
    public ResponseModel updateuser(User user) throws IOException {
        ResponseModel responseModel = new ResponseModel();
        List<User> userData = readJsonData();

        userData.removeIf(usr->usr.getEmail().equals(user.getEmail()));
        userData.add(user);

        boolean status = writeJsonData(userData);
        return responseModel;
    }

    @Override
    public ResponseModel deleteuser(String userid) throws IOException {
        ResponseModel responseModel = new ResponseModel();
        List<User> userData = readJsonData();
        userData.removeIf(usr->usr.getEmail().equals(userid));
        boolean status = writeJsonData(userData);
        return responseModel;
    }

    public List<User> readJsonData(){
        List<User> user = new ArrayList<>();
        try{
            String json = Files.readString(Path.of("user.json"));
            user = new Gson().fromJson(json, new TypeToken<List<User>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean writeJsonData(List<User> userData) throws IOException {
        boolean status = false;
        try (FileWriter file = new FileWriter("user.json")){
            file.write(new Gson().toJson(userData));
            file.flush();

            status=true;
        }
        return status;
    }
}
