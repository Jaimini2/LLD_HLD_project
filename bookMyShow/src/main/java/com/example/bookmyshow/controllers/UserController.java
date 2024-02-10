package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.dtos.SignUpRequestDTO;
import com.example.bookmyshow.dtos.SignUpResponseDTO;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        SignUpResponseDTO signUpResponse = new SignUpResponseDTO();

         try{
           User user =  userService.signUp(signUpRequestDTO.getEmail(),
                    signUpRequestDTO.getPassword());
            signUpResponse.setUserId(user.getId());
            signUpResponse.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            signUpResponse.setResponseStatus(ResponseStatus.FAILURE);
        }
        return signUpResponse;
    }
}
