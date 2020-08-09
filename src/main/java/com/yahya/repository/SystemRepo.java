package com.yahya.repository;

import com.yahya.entities.System;
import com.yahya.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Time;

public interface SystemRepo extends JpaRepository<System, Long> {

    System findByUser(User user);
}
