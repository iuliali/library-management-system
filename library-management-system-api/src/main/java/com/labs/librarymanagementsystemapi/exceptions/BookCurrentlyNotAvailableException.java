package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class BookCurrentlyNotAvailableException extends BadRequestException {
    private static final String EXCEPTION_MESSAGE = "Book Currently Not Available!";

    public BookCurrentlyNotAvailableException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }
}
