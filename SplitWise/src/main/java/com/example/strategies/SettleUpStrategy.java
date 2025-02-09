package com.example.strategies;

import com.example.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    public List<Expense> settleUp(List<Expense> expenses);
}
