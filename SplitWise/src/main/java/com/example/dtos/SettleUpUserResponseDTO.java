package com.example.dtos;

import com.example.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SettleUpUserResponseDTO {

    private List<Expense> transactions;
}
