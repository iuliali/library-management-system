package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class NegativeYearException extends NotValidDataException {
    private static final String EXCEPTION_MESSAGE = "Year cannot be a negative number!";

    public NegativeYearException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }
}
