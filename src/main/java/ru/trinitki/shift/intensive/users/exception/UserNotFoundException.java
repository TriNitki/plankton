package ru.trinitki.shift.intensive.users.exception;

import ru.trinitki.shift.intensive.errors.CodeAbleException;

public class UserNotFoundException extends CodeAbleException {
    public UserNotFoundException() {
        super(404, "User not found!");
    }
}
