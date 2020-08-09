package com.yahya.services;

import com.yahya.entities.User;
import com.yahya.exceptions.SuccessEntity;
import com.yahya.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    HttpServletRequest httpServletRequest;

    public void save(User profile){
        userRepo.save(profile);
    }

    public User loadProfile(Long profileId) {
        // TODO loading profile data

        User user = userRepo.findFirstById(profileId);

        return user;
    }

    public SuccessEntity editByEmail(String email, User profileDTO) {

        User updatedUser = userRepo.findByEmail(email);

        if(profileDTO.getName() != null)
            updatedUser.setName(profileDTO.getName());

        if(profileDTO.getPassword() != null)
            updatedUser.setPassword(profileDTO.getPassword());

        if(profileDTO.getPhone() != null)
            updatedUser.setPhone(profileDTO.getPhone());

        User profile = userRepo.save(updatedUser);

        return new SuccessEntity(200, profile, null);
    }

    public void changePassword(String email1) {
        User user = userRepo.findByEmail(email1);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.

        String token = String.format("%08d", number);
        user.setPassword(token);
        userRepo.save(user);


        String recipientAddress = user.getEmail();
        String subject = "Adel - Registration Confirmation";
        String message = "Thank you for creating account with Shower System, " + user.getName() +
                "\n\nWelcome to Shower System's Application!\n" +
                "your Password has been changed to " + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);

    }
}

