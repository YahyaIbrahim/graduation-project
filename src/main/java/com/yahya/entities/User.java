package com.yahya.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahya.DTO.RegistrationDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
@Proxy(lazy = false)
public class User extends AuditModel{

    @Column(name = "enabled")
    private boolean enabled;

    @NotNull
    @Column(name = "name")
    @Pattern(regexp = "[\\w\\s]+")
    private String name;


    @NotNull
    @Size(min = 8, max = 60, message = "(Error: Password should be from 8 to 60 characters)")
    @Column(name = "password")
    private String password;


    @NotNull
    @Column(name = "email")
    //@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    @Email(message = "(Error: Please enter a valid email address)")
    private String email;

    @Size(min = 11, message = "(Error: enter a valid phone number")
    @Column(name = "phone")
    @Pattern(regexp = "^[0-9]*$")
    private String phone;


    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<System> systems;

    public User() {
    }

    public User(RegistrationDTO registrationDTO){
        this.name = registrationDTO.getName();
        this.email =registrationDTO.getEmail();
        this.password = registrationDTO.getPassword();
        this.phone = registrationDTO.getPhone();
        this.enabled = false;

    }

}
