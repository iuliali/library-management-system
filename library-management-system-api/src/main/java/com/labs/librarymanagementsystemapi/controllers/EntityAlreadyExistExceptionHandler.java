package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityAlreadyExistExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            AuthorNameAlreadyExistException.class,
            BookAlreadyExistException.class
    })
    public ResponseEntity<?> handleBadRequest(EntityAlreadyExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {Exception.class, Error.class})
    public ResponseEntity<String> handleInternalServerError (Throwable exception, WebRequest request) {

        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}