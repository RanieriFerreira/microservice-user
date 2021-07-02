package com.studies.microserviceuser.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserPutRequest {

    @NotEmpty(message = "The user id cannot be empty")
    private Long userId;

    @NotEmpty(message = "The user name cannot be empty")
    private String name;
}
