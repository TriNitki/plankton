package ru.trinitki.shift.intensive.events.dto.EventGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventGroupCreateResponseDto extends EventGroupCreateRequestDto {
    @JsonProperty("event_group_id")
    private UUID eventGroupId;

    public EventGroupCreateResponseDto(UUID eventGroupId) {
        this.eventGroupId = eventGroupId;
    }

    public EventGroupCreateResponseDto(LocalDate date, LocalTime time, String description, UUID sectionId, ReplayDto replay, UUID eventGroupId) {
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
