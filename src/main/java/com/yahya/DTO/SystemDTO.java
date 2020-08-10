package com.yahya.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class SystemDTO {

    @NotNull
    private String temperature = "0";

    @NotNull
    private String time ="10:00";

    @NotNull
    private String liter = "0";



}
