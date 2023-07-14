package ru.trinitki.shift.intensive.events.dto.Event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventCreateResponseDto extends EventCreateRequestDto {
    @JsonProperty("event_id")
    private final UUID eventId;
    @JsonProperty("event_group_id")
    private final UUID eventGroupId;
    @JsonProperty("owner_id")
    private final UUID ownerId;

    public EventCreateResponseDto(UUID ownerId, LocalDate date, LocalTime time, String description, UUID eventId, UUID eventGroupId) {
        super(date, time, description);
        this.ownerId = ownerId;
        this.eventId = eventId;
        this.eventGroupId = eventGroupId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getEventGroupId() {
        return eventGroupId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}
