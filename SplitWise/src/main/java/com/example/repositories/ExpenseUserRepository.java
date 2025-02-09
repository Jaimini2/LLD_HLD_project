package com.example.repositories;

import com.example.models.ExpenseUser;
import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseUserRepository  extends JpaRepository<ExpenseUser,Long> {

    List<ExpenseUser> findAllByUser(User user);
}
