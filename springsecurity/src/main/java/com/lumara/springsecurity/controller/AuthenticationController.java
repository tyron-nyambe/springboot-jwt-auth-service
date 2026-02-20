package com.lumara.springsecurity.controller;

import com.lumara.springsecurity.dto.SignUpRequest;
import com.lumara.springsecurity.entities.User;
import com.lumara.springsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    //Injecting authentication service
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest)
    {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
}
