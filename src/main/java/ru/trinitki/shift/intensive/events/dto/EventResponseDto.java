package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventResponseDto extends EventRequestDto {
    @JsonProperty("event_id")
    private final UUID eventId;
    @JsonProperty("event_group_id")
    private final UUID eventGroupId;
    @JsonProperty("section_id")
    private final UUID sectionId;

    public EventResponseDto(UUID ownerId, LocalDate date, LocalTime time, String description, UUID eventId, UUID eventGroupId, UUID sectionId) {
        super(ownerId, date, time, description);
        this.eventId = eventId;
        this.eventGroupId = eventGroupId;
        this.sectionId = sectionId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getEventGroupId() {
        return eventGroupId;
    }

    public UUID getSectionId() {
        return sectionId;
    }
}
