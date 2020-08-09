package com.yahya.controllers;



import com.yahya.DTO.EmailDTO;
import com.yahya.DTO.SystemDTO;
import com.yahya.entities.System;
import com.yahya.entities.User;
import com.yahya.exceptions.SuccessEntity;
import com.yahya.exceptions.SuccessString;
import com.yahya.exceptions.SuccessSystem;
import com.yahya.services.SystemService;
import com.yahya.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "User APIs", tags = {"User Management"},
        description = "User operation")
public class UserController {
    @Autowired
    private UserService profileService;

    @Autowired
    private SystemService systemService;


    @ApiOperation(value = "Updates profile.")
    @PostMapping(path = "/edit/{email}",produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessEntity editByEmail(@PathVariable("email") String email, @RequestBody User profile) {
        return profileService.editByEmail(email, profile);
    }




    @ApiOperation(value = "change password")
    @PostMapping(path = "/changepassword",produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessString changePassword(@RequestBody EmailDTO email) {
        try {
            profileService.changePassword(email.getEmail());
            return new SuccessString(200,  "please check your email", null);
        }catch (NullPointerException d) {
            return new SuccessString(400,  null, "this email didn't signup ");
        }

    }


    @ApiOperation(value = "System")
    @PostMapping(path = "{email}/system-post",produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessString systemPost(@PathVariable("email") String email, @RequestBody SystemDTO systemDTO) {
        try {
            systemService.save(systemDTO,email);
            return new SuccessString(200,  "Done", null);
        }catch (NullPointerException d) {
            return new SuccessString(400,  null, "error");
        }

    }


    @ApiOperation(value = "System")
    @GetMapping(path = "{email}/system-get",produces = "application/json")
    public SuccessSystem systemPost(@PathVariable("email") String email) {
        try {
            User user = profileService.loadByEmail(email);
            System system = systemService.find(user);
            return new SuccessSystem(200,  system, null);
        }catch (NullPointerException d) {
            return new SuccessSystem(400,  null, "error");
        }

    }











}
