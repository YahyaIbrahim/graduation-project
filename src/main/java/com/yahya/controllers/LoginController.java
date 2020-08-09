package com.yahya.controllers;



import com.yahya.DTO.LoginDTO;
import com.yahya.entities.User;
import com.yahya.exceptions.SuccessEntity;
import com.yahya.services.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Login APIs", tags = {"Login"},
        description = "Login operation")

public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Login with Email , Password")
    @PostMapping(path = "/login", produces = "application/json")
    public SuccessEntity Login(@RequestBody LoginDTO profileDTO) {

        // String password = new BCryptPasswordEncoder().encode(profileDTO.getPassword());
        User profile = loginService.login(profileDTO.getEmail(),profileDTO.getPassword());
        if(profile != null)
            /*
            return profile;
        return null;
*/

            return new SuccessEntity(200, profile, null);
        return new SuccessEntity(400, null,"User Not Found");


    }

}
