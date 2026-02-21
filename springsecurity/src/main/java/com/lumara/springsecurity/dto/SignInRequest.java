package com.lumara.springsecurity.dto;

import lombok.Data;

@Data //To get the getters and setters for the variables
public class SignInRequest {

    private String email;

    private String password;
}
