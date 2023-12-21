package com.example.demo.Contoller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.models.users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;
//    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserService userService;
//    @GetMapping("/test")
//    public List<users> test(){
//        return this.userService.getusersList();
//    }


    @GetMapping("/users")
    public List<users> getAllUsers(){
        return userRepository.findAll();

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<users> updateUser(@PathVariable int id, @RequestBody users userDetails){
        users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found") );

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());

        users updatedEmployee = userRepository.save(user);
        return ResponseEntity.ok(updatedEmployee);
    }

//    @PostMapping("/users")
//    public users createEmployee(@RequestBody users employee) {
//        employee.setRole("ROLE_"+ employee.getRole().toUpperCase());
////        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//        return userRepository.save(employee);

//    @GetMapping("/lu")
//    public String getloggeduser(Principal principal){
//        return (principal.getName());
//    }

    @DeleteMapping("/user/{userid}")
    private void deleteUser(@PathVariable("userid") int userid) {
        userRepository.deleteById(userid);}

    @GetMapping("/users/{email}")
    public ResponseEntity<users> getCustomerByEmailHandler(@PathVariable("email") String email){


        users user = userRepository.findByEmail(email);

        return ResponseEntity.ok(user);

//    @GetMapping("/abc")
//    public String abc(){
//        return ("hello");
//    }


}}


