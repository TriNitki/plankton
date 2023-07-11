package ru.trinitki.shift.intensive.users.exception;

import ru.trinitki.shift.intensive.errors.CodeAbleException;

public class UserForbiddenException extends CodeAbleException {
    public UserForbiddenException() {
        super(403, "Access to this recourse on the server is denied!");
    }
}
