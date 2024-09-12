package com.NNTeachie.service;


import com.NNTeachie.dtos.SignUpDetailsRequest;
import com.NNTeachie.dtos.SignUpDetailsResponse;
import com.NNTeachie.dtos.UserLogin;
import com.NNTeachie.entity.SignUpDetails;
import com.NNTeachie.entity.UserDetails;
import com.NNTeachie.repo.SignUpDetailsRepo;
import com.NNTeachie.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private SignUpDetailsRepo signUpDetailsRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String createUser(SignUpDetailsRequest userSignUp) {
        if (userSignUp.getPassward() != null) {
            userSignUp.setPassward(encoder.encode(userSignUp.getPassward()));
        }
        if (userSignUp.getRole() == null) {
            userSignUp.setRole("USER");
        }
        UserDetails userDetails1 = new UserDetails();
        UserDetails save = null;
        SignUpDetails save1 = null;
        if (userSignUp != null) {
            userDetails1.setUserName(userSignUp.getUserName());
            userDetails1.setPassword(userSignUp.getPassward());
            userDetails1.setRole(userSignUp.getRole());
            save = userDetailsRepo.save(userDetails1);
            save1 = signUpDetailsRepo.save(dtoToEntity(userSignUp));
        }

        if (save != null && save1 != null) {
            return "SucessFully Registered";
        }
        return "Registration Failed";
    }

    public String verfy(UserLogin userLogin) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin
                        .getUserName(), userLogin.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userLogin.getUserName());
        }
        return "Failed";
    }

    public SignUpDetailsResponse getUser(String userName) {
        SignUpDetails byUserName = signUpDetailsRepo.findByUserName(userName);
        if (byUserName == null) {
            throw new UsernameNotFoundException("User with username " + userName + " not found");
        }
        return entityToMap(byUserName);
    }

    private SignUpDetailsResponse entityToMap(SignUpDetails byUserName) {
        SignUpDetailsResponse response = new SignUpDetailsResponse();
        response.setFullName(byUserName.getFullName());
        response.setUserName(byUserName.getUserName());
        response.setId(byUserName.getId());
        response.setPhNumber(byUserName.getPhNumber());
        response.setRole(byUserName.getRole());
        response.setDate(byUserName.getDate());
        return response;
    }


    private SignUpDetails dtoToEntity(SignUpDetailsRequest request) {
        SignUpDetails signUpDetails = new SignUpDetails();
        signUpDetails.setFullName(request.getFullName());
        signUpDetails.setUserName(request.getUserName());
        signUpDetails.setPassward(request.getPassward());
        signUpDetails.setRole(request.getRole());
        signUpDetails.setDate(new Date());
        signUpDetails.setPhNumber(request.getPhNumber());
        return signUpDetails;
    }
}
