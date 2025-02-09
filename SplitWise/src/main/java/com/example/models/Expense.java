package com.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Expense extends BaseModel{
    private String description;

    private int amount;

    @ManyToOne
    private User createdBy;

    @Enumerated(EnumType.ORDINAL)
    private  ExpenseType expenseType;

    @ManyToOne
    private Group group;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "expense")
    List<ExpenseUser> expenseUserList;


}
