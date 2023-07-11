package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventRequestDto {
    @JsonProperty("owner_id")
    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    public EventRequestDto(LocalDate date, LocalTime time, String description) {
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
