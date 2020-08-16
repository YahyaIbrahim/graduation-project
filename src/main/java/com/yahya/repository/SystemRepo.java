package com.yahya.repository;

import com.yahya.entities.System;
import com.yahya.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
@Repository
public interface SystemRepo extends JpaRepository<System, Long> {

    System findTopByUserOrderByIdDesc(User user);
}
