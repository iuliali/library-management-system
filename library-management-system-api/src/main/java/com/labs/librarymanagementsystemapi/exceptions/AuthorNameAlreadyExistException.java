package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class AuthorNameAlreadyExistException extends EntityAlreadyExistException {
    private static final String EXCEPTION_MESSAGE = "Author Name Already Exists!";

    public AuthorNameAlreadyExistException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }
}
