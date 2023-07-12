package ru.trinitki.shift.intensive.events.dto.Event;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventCreateRequestDto {
    @NotNull
    private final LocalDate date;
    @NotNull
    private final LocalTime time;
    @NotEmpty
    private final String description;
    @JsonProperty("section_id")
    private final UUID sectionId;

    public EventCreateRequestDto(LocalDate date, LocalTime time, String description, UUID sectionId) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.sectionId = sectionId;
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

    public UUID getSectionId() {
        return sectionId;
    }
}
