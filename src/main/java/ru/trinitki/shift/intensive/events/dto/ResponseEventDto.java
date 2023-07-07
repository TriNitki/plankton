package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ResponseEventDto extends RequestEventDto { // Исключить наследование
    @JsonProperty("section_id")
    private UUID sectionId;

    public ResponseEventDto(LocalDate date, LocalTime time, String description, Replay replay, UUID sectionId) {
        super(date, time, description, replay);
        this.sectionId = sectionId;
    }

    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
    }
}
