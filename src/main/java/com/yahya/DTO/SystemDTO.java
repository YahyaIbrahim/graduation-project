package com.yahya.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class SystemDTO {

    @NotNull
    private String temperature ;

    @NotNull
    private String time ;

    @NotNull
    private String liter ;



}
