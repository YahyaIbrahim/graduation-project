package com.yahya.services;

import com.yahya.DTO.SystemDTO;
import com.yahya.entities.System;
import com.yahya.entities.User;
import com.yahya.repository.SystemRepo;
import com.yahya.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

    @Autowired
    SystemRepo systemRepo;

    @Autowired
    UserRepo userRepo;

    public void save(SystemDTO systemDTO, String email){
        User user = userRepo.findByEmail(email);
        System system = new System(systemDTO);
        system.setUser(user);
        systemRepo.save(system);

    }


    public System find(User user){
        return systemRepo.findTopByUserOrderByIdDesc(user);
    }
}
