package com.yahya.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class SuccessList {
    private final int code;
    private final List message;
    private final String error;
}
