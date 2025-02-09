package com.example.repositories;

import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository  extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);
}
