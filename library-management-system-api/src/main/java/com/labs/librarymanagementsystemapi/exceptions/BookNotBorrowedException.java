package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class BookNotBorrowedException extends EntityDoesNotExistException {
    private static final String EXCEPTION_MESSAGE = "Book was not borrowed by current user!";

    public BookNotBorrowedException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }
}
