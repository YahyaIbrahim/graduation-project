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
        system.setLiter("");
        system.setTemperature("");
        system.setTime("");
        system.setUser(user);
        systemRepo.save(system);

    }

    public SuccessSystem edit(System systemDTO,String email){
        User user = userRepo.findByEmail(email);
        System system = systemRepo.findTopByUserOrderByIdDesc(user);
        java.lang.System.out.println("First Test");
        java.lang.System.out.println(system.getLiter());
        java.lang.System.out.println(system.getTemperature());
        java.lang.System.out.println(system.getTime());
        java.lang.System.out.println("Second Test");

        if(systemDTO.getLiter() == null ) {
            system.setLiter(system.getLiter());
            systemRepo.save(system);
        }
        if(systemDTO.getTemperature() == null ) {
            system.setTemperature(system.getTemperature());
            systemRepo.save(system);
        }
        if(systemDTO.getTime() == null ) {
            system.setTime(system.getTime());
            systemRepo.save(system);
        }
        if(systemDTO.getLiter() != null ) {
            java.lang.System.out.println(systemDTO.getLiter());
            java.lang.System.out.println(" Test");
            java.lang.System.out.println(system.getLiter());
            system.setLiter(systemDTO.getLiter());
             systemRepo.save(system);
        }
        if(systemDTO.getTemperature() != null ) {
            java.lang.System.out.println(systemDTO.getTemperature());
            java.lang.System.out.println(" Test");
            java.lang.System.out.println(system.getTemperature());
            system.setTemperature(systemDTO.getTemperature());
            systemRepo.save(system);
        }
        if(systemDTO.getTime() != null ) {
            java.lang.System.out.println(systemDTO.getTime());
            java.lang.System.out.println(" Test");
            java.lang.System.out.println(system.getTime());
            system.setTime(systemDTO.getTime());
            systemRepo.save(system);
        }


        return new SuccessSystem(200, system, null);

    }


    public System find(User user){
        return systemRepo.findTopByUserOrderByIdDesc(user);
    }
}
