package com.yahya.exceptions;


import com.yahya.entities.User;
import lombok.Data;

@Data
public class SuccessEntity {
    private final int code;
    private final User message;
    private final String error;
}
