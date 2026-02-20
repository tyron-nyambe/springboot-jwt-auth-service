package com.lumara.springsecurity.services;

import com.lumara.springsecurity.dto.SignUpRequest;
import com.lumara.springsecurity.entities.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);
}
