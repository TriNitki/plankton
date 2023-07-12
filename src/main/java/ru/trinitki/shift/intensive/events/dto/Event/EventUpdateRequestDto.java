package ru.trinitki.shift.intensive.events.dto.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventUpdateRequestDto {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    public EventUpdateRequestDto(LocalDate date, LocalTime time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
