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
    @Column(value = "event_group_id")
    private UUID eventGroupId;
    @Column(value = "event_id")
    private UUID eventId;

    public Events() {
    }

    public Events(UUID eventId, String description, UUID ownerId, UUID eventGroupId, LocalTime time, LocalDate date) {
        this.eventId = eventId;
        this.description = description;
        this.key.ownerId = ownerId;
        this.eventGroupId = eventGroupId;
        this.key.time = time;
        this.key.date = date;
    }

    public static class Key {
    public UUID getOwnerId() {
        return this.key.ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.key.ownerId = ownerId;
    }

    public LocalDate getDate() {
        return this.key.date;
    }

    public void setDate(LocalDate date) {
        this.key.date = date;
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
        return this.eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public LocalTime getTime() {
        return this.key.time;
    }

    public void setTime(LocalTime time) {
        this.key.time = time;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "owner_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0, value = "owner_id")
        private UUID ownerId;
        @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.CLUSTERED, ordinal = 1, value = "date")
        private LocalDate date;
        @PrimaryKeyColumn(name = "time", type = PrimaryKeyType.CLUSTERED, ordinal = 2, value = "time")
        private LocalTime time;

        public Key() {
        }
    }
}
