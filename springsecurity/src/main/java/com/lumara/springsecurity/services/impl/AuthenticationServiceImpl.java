package com.lumara.springsecurity.services.impl;

import com.lumara.springsecurity.dto.SignUpRequest;
import com.lumara.springsecurity.entities.Role;
import com.lumara.springsecurity.entities.User;
import com.lumara.springsecurity.repository.UserRepository;
import com.lumara.springsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


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

}
