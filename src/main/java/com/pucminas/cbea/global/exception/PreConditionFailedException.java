package com.pucminas.cbea.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class PreConditionFailedException extends RuntimeException {

    public PreConditionFailedException() {
        super("Falha na condição pré estabelecida.");
    }

    public PreConditionFailedException(String message) {
        super(message);
    }
}
