package com.taskify.taskify.exception;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException(Integer id) {
        super("Could not find the user with id: " + id);
    }
}
