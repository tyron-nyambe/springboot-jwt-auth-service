package com.lumara.springsecurity.services.impl;

import com.lumara.springsecurity.dto.JwtAuthenticationResponse;
import com.lumara.springsecurity.dto.SignInRequest;
import com.lumara.springsecurity.dto.SignUpRequest;
import com.lumara.springsecurity.entities.Role;
import com.lumara.springsecurity.entities.User;
import com.lumara.springsecurity.repository.UserRepository;
import com.lumara.springsecurity.services.AuthenticationService;
import com.lumara.springsecurity.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    //Method to accept sign up user and save in the repository
    public User signUp(SignUpRequest signUpRequest)
    {
        User user= new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setSecondname(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest)
    {
        //Authenticate user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail()
                ,signInRequest.getPassword()));

        //Fetch user from DB and user jwtservice to generate token
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException ("Invalid email or password"));

        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);

        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;


    }

}
