package com.example.command;

import com.example.controllers.SettleUpController;
import com.example.dtos.SettleUpUserGroupRequestDTO;
import com.example.dtos.SettleUpUserRequestDTO;
import com.example.dtos.SettleUpUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUserCommand implements Command{

    @Autowired
    private SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController   settleUpController){
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        if(words.size() == 2 && words.get(0).equals("SettleUpUser")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        Long userId = Long.valueOf(words.get(1));

        SettleUpUserRequestDTO request = new SettleUpUserRequestDTO();
        request.setUserId(userId);

        SettleUpUserResponseDTO settleUpUserResponseDTO = settleUpController.settleUpUser(request);
    }
}
