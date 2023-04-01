package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NegativeYearException.class})
    public ResponseEntity<?> handleBadRequest(NegativeYearException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> Negative Value Not Accepted for Year ",
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {NegativeNumberCopiesException.class,})
    public ResponseEntity<?> handleBadRequest(NegativeNumberCopiesException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> Negative Value Not Accepted Number Of Copies",
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BorrowBookAlreadyBorrowedException.class,})

    public ResponseEntity<?> handleBadRequest(BorrowBookAlreadyBorrowedException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> You already borrowed the book!", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {
            BookCurrentlyNotAvailableException.class
    })
    public ResponseEntity<?> handleBadRequest(BookCurrentlyNotAvailableException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> There are No Copies of this Book available!", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {AuthorDoesNotExistException.class})
    public ResponseEntity<?> handleBadRequest(AuthorDoesNotExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred- author DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BookDoesNotExistException.class,})
    public ResponseEntity<?> handleBadRequest(BookDoesNotExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred - BOOK DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserDoesNotExistException.class})
    public ResponseEntity<?> handleBadRequest(UserDoesNotExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred- User DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AuthorNameAlreadyExistException.class,})
    public ResponseEntity<?> handleBadRequest(AuthorNameAlreadyExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> Author Name Already Exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {BookAlreadyExistException.class})
    public ResponseEntity<?> handleBadRequest(BookAlreadyExistException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred ->Book Already Exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {Exception.class, Error.class})
    public ResponseEntity<String> handleInternalServerError (Throwable exception, WebRequest request) {

        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



