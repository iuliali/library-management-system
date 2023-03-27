package com.labs.librarymanagementsystemapi.exceptions;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.time.Instant;

public class EntityAlreadyExistException extends Exception {

    private final Instant issuedAt;
    private final Type issuedInClass;

    public EntityAlreadyExistException(String message,
                                       Type issuedInClass) {
        super(message);
        this.issuedAt = Instant.now();
        this.issuedInClass = issuedInClass;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format(
                "Name Entity Already Exist issuedAt: {0} in class {1} with message {2}",
                this.issuedAt,
                this.issuedInClass,
                super.getMessage());
    }
}
