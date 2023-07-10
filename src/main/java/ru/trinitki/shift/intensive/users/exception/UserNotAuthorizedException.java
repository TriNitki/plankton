package ru.trinitki.shift.intensive.users.exception;

import ru.trinitki.shift.intensive.errors.CodeAbleException;

public class UserNotAuthorizedException extends CodeAbleException {
    public UserNotAuthorizedException() {
        super(401, "User is not authorized!");
    }
}
