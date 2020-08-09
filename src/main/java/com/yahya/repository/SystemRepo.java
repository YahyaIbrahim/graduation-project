package com.yahya.repository;

import com.yahya.entities.System;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Time;

public interface SystemRepo extends JpaRepository<System, Long> {

    System findAllByTemperatureAndLiterAndTime( int temperature,  int liter,  Time time);
}
