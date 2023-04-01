package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice

public class EntityDoesNotExistExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            AuthorDoesNotExistException.class,
            BookDoesNotExistException.class,
            UserDoesNotExistException.class
    })
    public ResponseEntity<?> handleBadRequest(AuthorDoesNotExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred- author DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> handleBadRequest(BookDoesNotExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred - BOOK DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> handleBadRequest(UserDoesNotExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred- User DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {Exception.class, Error.class})
    public ResponseEntity<String> handleInternalServerError (Throwable exception, WebRequest request) {

        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
