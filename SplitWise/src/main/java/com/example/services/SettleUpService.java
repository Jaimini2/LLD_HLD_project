package com.example.services;

import com.example.models.Expense;
import com.example.models.ExpenseUser;
import com.example.models.Group;
import com.example.models.User;
import com.example.repositories.ExpenseRepository;
import com.example.repositories.ExpenseUserRepository;
import com.example.repositories.GroupRespository;
import com.example.repositories.UserRespository;
import com.example.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettleUpService {


    public SettleUpService(){

    }

    private GroupRespository groupRespository;
    private ExpenseRepository expenseRepository;
    private SettleUpStrategy settleUpStrategy;
    private UserRespository userRespository;

    private ExpenseUserRepository expenseUserRepository;
    @Autowired
    public SettleUpService(
            GroupRespository groupRespository,
            ExpenseRepository expenseRepository,
            SettleUpStrategy settleUpStrategy,
            UserRespository userRespository,
            ExpenseUserRepository expenseUserRepository
    ) {
        this.groupRespository = groupRespository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.userRespository = userRespository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public List<Expense> settleUpUser(
            Long userId
    ) {
        //1. validate the user
        Optional<User> userOptional = userRespository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found! ");
        }

        User user = userOptional.get() ;
        //2 . Getting all the expenses related to the user.
        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);
        Set<Expense> expenseSet = new HashSet<>();

        for(ExpenseUser expenseUser : expenseUsers){
            expenseSet.add(expenseUser.getExpense());
        }
        //3.Iterate through all the expenses to find out who owes what
        //4. Find transactions to be done
        List<Expense> transactions = settleUpStrategy.settleUp(expenseSet.stream().toList());
        //5 . return the transactions
        return transactions;
    }

    public List<Expense> settleUpGroup(
            Long groupId
    ) {
        Optional<Group> optionalGroup = groupRespository.findById(groupId);
        //1 . Validate the group
        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Group doesn't exist ");
        }

        Group group = optionalGroup.get();

        //2. Extract all the expenses
        List<Expense>  expenses = expenseRepository.findAllByGroup(group);
        //3. Iterate through  all the expenses to find out who owes what
        //4.Find the transactions to be done
        List<Expense> transactions = settleUpStrategy.settleUp(expenses);

        //5.Return transactions
        return transactions;
    }
}