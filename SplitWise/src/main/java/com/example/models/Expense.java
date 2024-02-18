package com.example.models;

import jakarta.persistence.Entity;

@Entity
public class Expense extends BaseModel{
    private String description;
}
