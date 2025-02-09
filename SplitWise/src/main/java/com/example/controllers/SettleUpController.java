package com.example.controllers;

import com.example.dtos.SettleUpUserGroupRequestDTO;
import com.example.dtos.SettleUpUserGroupResponseDTO;
import com.example.dtos.SettleUpUserRequestDTO;
import com.example.dtos.SettleUpUserResponseDTO;
import com.example.exceptions.UserNotFoundException;
import com.example.models.Expense;
import com.example.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    SettleUpService settleUpService;

    public SettleUpController(){
        settleUpService = new SettleUpService( );
    }

        public SettleUpController(SettleUpService settleUpService){
            this.settleUpService = settleUpService;
        }

    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO settleUserReq){

        SettleUpUserResponseDTO settleUpUserResponseDTO = new SettleUpUserResponseDTO();
        try{
            List<Expense> transactions = settleUpService.settleUpUser(settleUserReq.getUserId());

            if(transactions.isEmpty()){
                throw new UserNotFoundException("User not Found");
            }


            settleUpUserResponseDTO.setTransactions(transactions);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return settleUpUserResponseDTO;
    }

    public SettleUpUserGroupResponseDTO settleUpUserGroup(SettleUpUserGroupRequestDTO settleUserGroupReq){
        SettleUpUserGroupResponseDTO settleUserGroupReqDTO = new SettleUpUserGroupResponseDTO();
        try{
            List<Expense> transactions = settleUpService.settleUpUser(settleUserGroupReq.getGroupId());


            settleUserGroupReqDTO.setTransactions(transactions);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return settleUserGroupReqDTO;
    }
}
