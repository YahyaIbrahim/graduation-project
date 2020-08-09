package com.yahya.services;


import com.yahya.entities.User;
import com.yahya.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {
    @Autowired
    UserRepo userRepository;
    @Autowired
    UserService userService;

    public User login(String email, String password){
        User profile =  userRepository.findByEmailAndPassword(email, password);

        if (profile != null){
          return   userService.loadProfile(profile.getId());
        }
      return null;
    }

}
