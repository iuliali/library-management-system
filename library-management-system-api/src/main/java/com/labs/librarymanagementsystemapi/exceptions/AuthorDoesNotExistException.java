package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class AuthorDoesNotExistException extends BadRequestException {
    private static final String EXCEPTION_MESSAGE = "Author Does Not Exist!";

    public AuthorDoesNotExistException( Type issuedInClass) {

        super(EXCEPTION_MESSAGE, issuedInClass);
    }

}
