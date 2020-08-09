package com.yahya.services;


import com.yahya.DTO.RegistrationDTO;
import com.yahya.entities.User;
import com.yahya.entities.VerificationToken;
import com.yahya.exceptions.SuccessEntity;
import com.yahya.repository.UserRepo;
import com.yahya.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class RegistrationService {

    @Autowired
    UserRepo userRepository;


    @Autowired
    JavaMailSender mailSender;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    VerificationTokenRepository verificationToken;

    private static final String SUCCESSFUL_REGISTRATION = "Registration completed successfully!";
    private static final String FAILED_REGISTRATION = "Email is already registered please use another one!";


    public SuccessEntity register(RegistrationDTO registrationDTO) {

        // COMPLETED check if email exist
        boolean exists = emailExist(registrationDTO.getEmail());


        // TODO return verfication code and should send an email
        if (!exists) {

            User profile = new User(registrationDTO);

            profile = userRepository.save(profile);

            confirmRegistration(profile.getId());
            return new SuccessEntity(200, profile, null);

        }
        return new SuccessEntity(400, null,FAILED_REGISTRATION);
    }

    private boolean emailExist(String email) {
        User profile = userRepository.findByEmail(email);

        if (profile != null) {
            return true;
        }
        return false;
    }

    private void confirmRegistration(Long userid) {
        User user = userRepository.findFirstById(userid);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.

        String token = String.format("%06d", number);
        VerificationToken myToken = new VerificationToken(token, user);
        verificationToken.save(myToken);

        String recipientAddress = user.getEmail();
        String subject = "Shower System - Thank you";
        String message = "Thank you for creating account with Shower System, " + user.getName() +
                "\n\nWelcome to Shower System's Application!\n" +
        "Please activate your account!\n\n" +
                "This number will be available for 24 hours only "
                ;


        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + token);
        mailSender.send(email);

    }




}
