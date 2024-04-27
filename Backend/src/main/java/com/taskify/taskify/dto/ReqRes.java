package com.taskify.taskify.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.taskify.taskify.model.Task;
import com.taskify.taskify.model.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String username;
    private String email;
    private String role;
    private String password;
    private List<Task> tasks;
    private User ourUsers;
}
