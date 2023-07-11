package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventRequestDto {
    @JsonProperty("owner_id")
    private final UUID ownerId;
    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    public EventRequestDto(UUID ownerId, LocalDate date, LocalTime time, String description) {
        this.ownerId = ownerId;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public UUID getOwnerId() {
        return ownerId;
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
