package com.example.repositories;

import com.example.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  GroupRespository  extends JpaRepository<Group,Long> {

    @Override
    Optional<Group> findById(Long aLong);
}
