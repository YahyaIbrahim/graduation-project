package com.yahya.controllers;


import com.yahya.DTO.RegistrationDTO;
import com.yahya.DTO.TokenDTO;
import com.yahya.entities.User;
import com.yahya.entities.VerificationToken;
import com.yahya.exceptions.SuccessEntity;
import com.yahya.repository.VerificationTokenRepository;
import com.yahya.services.RegistrationService;
import com.yahya.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@Api(value = "Registration APIs", tags = {"Registration Management"},
        description = "Registration operations")
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    VerificationTokenRepository tokenRepository;


    @Autowired
    UserService userService;

    @PostMapping(path = "/signup")
    public SuccessEntity register(@RequestBody RegistrationDTO registrationDTO) {

        return registrationService.register(registrationDTO);
    }

    @ApiOperation(value = "registrationConfirm")
    @PostMapping(path = "/registrationConfirm")
    public SuccessEntity confirmRegistration
            (@RequestBody TokenDTO token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token.getToken());
        if (verificationToken == null) {
            String message = "Invalid token";
            return new SuccessEntity(400, null,message);
        }
        User user = verificationToken.getProfile();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = "Verification link expired!";
            return new SuccessEntity(400, null,messageValue);

        }

        user.setEnabled(true);
        userService.save(user);

        return new SuccessEntity(200, user, null);

    }


}
