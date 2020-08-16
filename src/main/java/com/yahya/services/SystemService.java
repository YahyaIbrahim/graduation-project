package com.yahya.services;

import com.yahya.DTO.SystemDTO;
import com.yahya.entities.System;
import com.yahya.entities.User;
import com.yahya.exceptions.SuccessEntity;
import com.yahya.exceptions.SuccessSystem;
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

    public void save(String email){
        User user = userRepo.findByEmail(email);
        System system = new System();
        system.setLiter("0");
        system.setTemperature("0");
        system.setTime("00:00");
        system.setUser(user);
        systemRepo.save(system);

    }

    public SuccessSystem edit(System systemDTO,String email){
        User user = userRepo.findByEmail(email);
        System system = systemRepo.findTopByUserOrderByIdDesc(user);

        if(systemDTO.getLiter() != null)
            system.setLiter(systemDTO.getLiter());

        if(systemDTO.getTemperature() != null)
            system.setTemperature(systemDTO.getTemperature());

        if(systemDTO.getTime() != null)
            system.setTime(systemDTO.getTime());

        systemRepo.save(system);

        return new SuccessSystem(200, system, null);

    }


    public System find(User user){
        return systemRepo.findTopByUserOrderByIdDesc(user);
    }
}
