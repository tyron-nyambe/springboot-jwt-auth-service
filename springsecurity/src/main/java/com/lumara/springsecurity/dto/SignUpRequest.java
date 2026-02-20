package com.lumara.springsecurity.dto;

import lombok.Data;

@Data //For getters and setters
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
