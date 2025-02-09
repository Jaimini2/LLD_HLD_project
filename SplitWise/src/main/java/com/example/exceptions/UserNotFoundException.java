package com.example.exceptions;

import com.example.models.User;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message){
        super(message);
    }
}
