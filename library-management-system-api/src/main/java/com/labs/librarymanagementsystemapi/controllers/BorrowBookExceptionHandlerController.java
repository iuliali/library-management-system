package com.labs.librarymanagementsystemapi.controllers;

import com.labs.librarymanagementsystemapi.exceptions.BookCurrentlyNotAvailableException;
import com.labs.librarymanagementsystemapi.exceptions.BorrowBookAlreadyBorrowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class BorrowBookExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            BorrowBookAlreadyBorrowedException.class,
            BookCurrentlyNotAvailableException.class
    })

    public ResponseEntity<?> handleBadRequest(BorrowBookAlreadyBorrowedException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> You already borrowed the book!", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> handleBadRequest(BookCurrentlyNotAvailableException exception,
                                              WebRequest request) {
        logger.warn(exception.getMessage());
        return new ResponseEntity<>("Exception occurred -> There are No Copies of this Book available!", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {Exception.class, Error.class})
    public ResponseEntity<String> handleInternalServerError (Throwable exception, WebRequest request) {

        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
