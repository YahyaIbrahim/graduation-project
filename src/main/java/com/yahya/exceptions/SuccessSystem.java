package com.yahya.exceptions;

import com.yahya.entities.System;
import lombok.Data;

@Data
public class SuccessSystem {
    private final int code;
    private final System message;
    private final String error;
}
