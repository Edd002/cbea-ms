package com.pucminas.cbea.global.exception;

public class EntityNotFoundException extends ApiException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
