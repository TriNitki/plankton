package ru.trinitki.shift.intensive.events.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("event_groups")
public class EventGroups {
    @PrimaryKey
    private Key key = new Key();
    @Column(value = "owner_id")
    private UUID ownerId;
    @Column(value = "section_id")
    private UUID sectionId;
    @Column
    private LocalDate date;
    @Column
    private LocalTime time;
    @Column
    private String description;
    @Column
    private Replay replay;

    @PrimaryKeyClass
    public static class Key {
    public UUID getEventGroupId() {
        return this.key.eventGroupId;
    }

    public void setEventGroupId(UUID eventGroupId) {
        this.key.eventGroupId = eventGroupId;
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Replay getReplay() {
        return this.replay;
    }

    public void setReplay(Replay replay) {
        this.replay = replay;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "event_group_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0, value = "event_group_id")
        private UUID eventGroupId;

        public Key() {
        }
    }
}
