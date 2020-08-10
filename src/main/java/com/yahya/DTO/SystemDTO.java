package com.yahya.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class SystemDTO {

    @NotNull
    private String temperature;

    @NotNull
    private String time;

    @NotNull
    private String liter;

    public SystemDTO() {
        this.liter = "0";
        this.temperature = "0";
        this.time = "10:00";


    }

}
