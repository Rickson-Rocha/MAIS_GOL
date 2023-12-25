package com.br.maisgol.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends Exception {
    public ConflictException(String message) {
        super(message);
    }
}
