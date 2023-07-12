package ru.trinitki.shift.intensive.events.dto.Event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventDto {
    @JsonProperty("event_id")
    private UUID eventId;
    @JsonProperty("event_group_id")
    private UUID eventGroupId;
    @JsonProperty("owner_id")
    private UUID ownerId;
    private LocalDate date;
    private LocalTime time;
    private String description;

    public EventDto() {
    }

    public EventDto(UUID eventId, UUID eventGroupId, UUID ownerId, LocalDate date, LocalTime time, String description) {
        this.eventId = eventId;
        this.eventGroupId = eventGroupId;
        this.ownerId = ownerId;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getEventGroupId() {
        return eventGroupId;
    }

    public void setEventGroupId(UUID eventGroupId) {
        this.eventGroupId = eventGroupId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
