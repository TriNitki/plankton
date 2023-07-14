package ru.trinitki.shift.intensive.events.exception;

import ru.trinitki.shift.intensive.errors.CodeAbleException;

public class EventIntervalBadRequestException extends CodeAbleException {
    public EventIntervalBadRequestException() {
        super(400, "Incorrect time interval entry!");
    }
}
