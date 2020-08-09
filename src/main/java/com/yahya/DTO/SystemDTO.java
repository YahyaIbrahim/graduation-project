package com.yahya.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;

@Data
public class SystemDTO {

    @NotNull
    private int temperature;

    @NotNull
    private String time;

    @NotNull
    private int liter;

}
