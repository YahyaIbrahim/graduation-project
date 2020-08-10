package com.yahya.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel
@Getter
@Setter
public class UserDTO {
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull
    @Column(name = "name")
    @Pattern(regexp = "[\\w\\s]+")
    private String name;


    @NotNull
    @Size(min = 8, max = 60, message = "(Error: Password should be from 8 to 60 characters)")
    @Column(name = "password")
    private String password;

    @Size(min = 11, message = "(Error: enter a valid phone number")
    @Column(name = "phone")
    @Pattern(regexp = "^[0-9]*$")
    private String phone;

}
