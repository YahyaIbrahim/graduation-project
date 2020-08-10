package com.yahya.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahya.DTO.SystemDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "sys")
@Proxy(lazy = false)
public class System extends AuditModel{

    @Column(name = "enabled")
    private boolean enabled;

    @NotNull
    @Column(name = "temperature")
    private String temperature ="0";

    @NotNull
    @Column(name = "time")
    private String time ="10:00";

    @NotNull
    @Column(name = "liter")
    private String liter ="0";

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public System() {
    }

    public System(SystemDTO systemDTO){
        this.liter = systemDTO.getLiter();
        this.temperature = systemDTO.getTemperature();
        this.time = systemDTO.getTime();
        this.enabled = true;

    }
}
