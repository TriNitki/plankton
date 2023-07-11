package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventGroupResponseDto extends EventGroupRequestDto {
    @JsonProperty("event_group_id")
    private UUID eventGroupId;

    public EventGroupResponseDto(UUID eventGroupId) {
        this.eventGroupId = eventGroupId;
    }

    public EventGroupResponseDto(LocalDate date, LocalTime time, String description, UUID sectionId, ReplayDto replay, UUID eventGroupId) {
        super(date, time, description, sectionId, replay);
        this.eventGroupId = eventGroupId;
    }

    public UUID getEventGroupId() {
        return eventGroupId;
    }

    public void setEventGroupId(UUID eventGroupId) {
        this.eventGroupId = eventGroupId;
    }
}
