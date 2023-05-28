package com.pucminas.cbea.global.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidApplicationTokenException extends ServiceException {

    public InvalidApplicationTokenException() {
        super("Token de aplicação inválido.");
    }

    public InvalidApplicationTokenException(String message) {
        super("Token de aplicação inválido.. " + message);
    }
}