package ru.trinitki.shift.intensive.events.exception;

import ru.trinitki.shift.intensive.errors.CodeAbleException;

public class EventNotFoundException extends CodeAbleException {
    public EventNotFoundException() {
        super(404, "Event not found!");
    }
}