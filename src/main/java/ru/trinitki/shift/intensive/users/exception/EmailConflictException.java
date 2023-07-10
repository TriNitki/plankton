package ru.trinitki.shift.intensive.users.exception;

import ru.trinitki.shift.intensive.errors.CodeAbleException;

public class EmailConflictException extends CodeAbleException {
    public EmailConflictException() {
        super(409, "User with this email already exists");
    }
}
