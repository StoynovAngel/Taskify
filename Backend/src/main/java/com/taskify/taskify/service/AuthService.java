package com.taskify.taskify.service;

import com.taskify.taskify.dto.ReqRes;
import com.taskify.taskify.model.User;
import com.taskify.taskify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes response = new ReqRes();
        try{
            User user = new User();

            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());
            user.setName(registrationRequest.getName());
            user.setUsername(registrationRequest.getUsername());

            User userResult = userRepository.save(user);
            if (userResult != null && userResult.getId()>0){
                response.setOurUsers(userResult);
                response.setMessage("User saved successfully");
                response.setStatusCode(200);
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
    public ReqRes signIn(ReqRes signInRequest){
        ReqRes response = new ReqRes();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
            System.out.println("User is: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully signed in");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User users = userRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)){
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
