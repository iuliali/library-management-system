package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class UserDoesNotExistException  extends EntityDoesNotExistException {
    private static final String EXCEPTION_MESSAGE = "User Does Not Exist!";

    public UserDoesNotExistException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }

}
