package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class BookAlreadyExistException extends EntityAlreadyExistException {
    private static final String EXCEPTION_MESSAGE = "Book Already Exists!";

    public BookAlreadyExistException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }

}
