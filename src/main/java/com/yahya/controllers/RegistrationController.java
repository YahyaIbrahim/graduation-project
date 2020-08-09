package com.yahya.controllers;


import com.yahya.DTO.RegistrationDTO;
import com.yahya.exceptions.SuccessEntity;
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
    UserService userService;

    @PostMapping(path = "/signup")
    public SuccessEntity register(@RequestBody RegistrationDTO registrationDTO) {

        return registrationService.register(registrationDTO);
    }

}
