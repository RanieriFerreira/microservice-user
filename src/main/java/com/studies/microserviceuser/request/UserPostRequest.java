package com.studies.microserviceuser.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserPostRequest {
    @NotEmpty(message = "The user name cannot be empty")
    private String name;

    @Email(message = "The email is not valid")
    @NotEmpty(message = "The user email cannot be empty")
    private String email;
}
