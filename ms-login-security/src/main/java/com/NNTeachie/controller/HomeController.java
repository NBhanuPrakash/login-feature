package com.NNTeachie.controller;

import com.NNTeachie.dtos.SignUpDetailsRequest;
import com.NNTeachie.dtos.SignUpDetailsResponse;
import com.NNTeachie.dtos.UserLogin;
import com.NNTeachie.entity.SignUpDetails;
import com.NNTeachie.entity.UserDetails;
import com.NNTeachie.service.JwtService;
import com.NNTeachie.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String welcome() {
        return "Welcome";
    }


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public SignUpDetailsResponse GetUser(@RequestHeader("Authorization") String authHeader) {
        // Extract the JWT token from the Authorization header
        String token = authHeader.substring(7); // Remove "Bearer " prefix

        // Extract the username from the token using JwtService
        String username = jwtService.extractUserName(token);

        // Use the username in your logic or return it as a response
        return userDetailsService.getUser(username);
    }

    @PostMapping("/register")
    public String saveUser(@RequestBody SignUpDetailsRequest userDetails) {
        return userDetailsService.createUser(userDetails);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userDetails) {
        return userDetailsService.verfy(userDetails);

    }
}
