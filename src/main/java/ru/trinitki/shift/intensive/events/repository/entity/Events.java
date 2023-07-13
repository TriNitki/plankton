package ru.trinitki.shift.intensive.events.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table(value = "events")
public class Events {
    @PrimaryKey
    private Key key = new Key();
    @Column
    private String description;
    @Column(value = "owner_id")
    private UUID ownerId;
    @Column(value = "event_group_id")
    private UUID eventGroupId;
    private LocalTime time;
    @Column
    private LocalDate date;

    public Events() {
    }

    public Events(UUID eventId, String description, UUID ownerId, UUID eventGroupId, LocalTime time, LocalDate date) {
        this.key.eventId = eventId;
        this.description = description;
        this.ownerId = ownerId;
        this.eventGroupId = eventGroupId;
        this.time = time;
        this.date = date;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "event_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0, value = "event_id")
        private UUID eventId;

        public Key() {
        }
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getEventGroupId() {
        return this.eventGroupId;
    }

    public void setEventGroupId(UUID eventGroupId) {
        this.eventGroupId = eventGroupId;
    }

    public UUID getEventId() {
        return this.key.eventId;
    }

    public void setEventId(UUID eventId) {
        this.key.eventId = eventId;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
