package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class BorrowBookAlreadyBorrowedException extends BorrowBookException {
    private static final String EXCEPTION_MESSAGE = "Book Already Borrowed!";

    public BorrowBookAlreadyBorrowedException(Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }
}
