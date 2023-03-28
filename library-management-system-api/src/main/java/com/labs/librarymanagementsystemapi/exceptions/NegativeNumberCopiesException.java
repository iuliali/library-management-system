package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;

public class NegativeNumberCopiesException extends NotValidDataException {
    private static final String EXCEPTION_MESSAGE = "Number of book copies cannot be negative!";

    public NegativeNumberCopiesException( Type issuedInClass) {
        super(EXCEPTION_MESSAGE, issuedInClass);
    }


}
