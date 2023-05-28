package com.pucminas.cbea.global.base;

import com.pucminas.cbea.global.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public abstract class BaseController {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, BindException.class })
    public ResponseEntity handleMethodArgumentNotValid(Exception ex) {
        List<String> errors = new ArrayList<>();

        if (ex instanceof MethodArgumentNotValidException) {
            errors.addAll(((MethodArgumentNotValidException) ex).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> String.format("Campo '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList()));
        }

        if (ex instanceof BindException) {
            errors.addAll(((BindException) ex).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> String.format("Campo '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList()));
        }

        if (ex instanceof HttpMessageNotReadableException) {
            errors.add(StringUtils.substringBefore(((HttpMessageNotReadableException) ex).getMessage(), "; nested exception is"));
        }

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity handleException(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof BadRequestException || ex instanceof InvalidApplicationTokenException || ex instanceof HttpMessageNotReadableException || ex instanceof MissingRequestHeaderException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof EntityNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        if (ex instanceof PreConditionFailedException) {
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        }

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        }

        if (ex instanceof MultipartException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }

        if (ex instanceof HttpException) {
            httpStatus = ((HttpException) ex).getHttpStatus();
        }

        return new ResponseEntity(List.of(ex.getMessage()), httpStatus);
    }
}