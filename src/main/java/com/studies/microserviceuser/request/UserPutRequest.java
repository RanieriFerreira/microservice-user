package com.studies.microserviceuser.request;

import lombok.Data;

@Data
public class UserPutRequest {
    private Long userId;
    private String name;
}
