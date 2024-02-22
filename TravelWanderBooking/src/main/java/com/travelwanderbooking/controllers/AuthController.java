package com.travelwanderbooking.controllers;

import com.travelwanderbooking.exceptions.UserNotFoundException;
import com.travelwanderbooking.payloads.LoginCredentials;
import com.travelwanderbooking.payloads.UserDTO;
import com.travelwanderbooking.security.JWTUtil;
import com.travelwanderbooking.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
     private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registerHandler(@Valid @RequestBody UserDTO user)
            throws UserNotFoundException{
           String encodedPass = passwordEncoder.encode(user.getPassword());

           user.setPassword(encodedPass);

           UserDTO userDTO = userService.registerUser(user);

           String token = jwtUtil.generateToken(userDTO.getEmail());

           return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("jwt-token", token),
                   HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, Object> logingHandler(@Valid @RequestBody LoginCredentials creadentials){

        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
            creadentials.getEmail(), creadentials.getPassword());

        authenticationManager.authenticate(authCredentials);

        String token = jwtUtil.generateToken(creadentials.getEmail());

        return Collections.singletonMap("jwt-token", token);

    }
}
