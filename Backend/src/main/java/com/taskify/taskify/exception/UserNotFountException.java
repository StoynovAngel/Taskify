package com.taskify.taskify.exception;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException(Long id) {
        super("Could not find the user with id: " + id);
    }
}
