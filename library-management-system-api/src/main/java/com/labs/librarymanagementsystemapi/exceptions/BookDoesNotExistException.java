package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class BookDoesNotExistException extends EntityDoesNotExistException {
    private static final String EXCEPTION_MESSAGE = "Book Does Not Exist!";

    public BookDoesNotExistException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }

}
