package com.example.demo.Contoller;

import com.example.demo.Security.JwtHelper;
import com.example.demo.models.JWTRequest;
import com.example.demo.models.JWTResponse;
import com.example.demo.models.users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper helper;

    //private Logger logger = LoggerFactory.getLogger(LoginController.class);


    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {


        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
//        if(userDetails.getPassword().equals(passwordEncoder.encode(request.getPassword())) ) {
//
//
//            String token = this.helper.generateToken(userDetails);
//
//            JWTResponse response = JWTResponse.builder()
//                    .jwtToken(token)
//                    .username(userDetails.getUsername()).build();
//            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid Password", HttpStatus.OK);
//        }
    }

    private void doAuthenticate(String email, String password) {


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @PostMapping("/create")
    public users createUser(@RequestBody users users){
        return userService.createUser(users);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
