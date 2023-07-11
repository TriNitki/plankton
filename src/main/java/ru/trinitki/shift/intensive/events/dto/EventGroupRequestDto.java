package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventGroupRequestDto {
    private LocalDate date;
    private LocalTime time;
    private String description;
    @JsonProperty("section_id")
    private UUID sectionId;
    private ReplayDto replay;

    public EventGroupRequestDto() {
    }

    public EventGroupRequestDto(LocalDate date, LocalTime time, String description, UUID sectionId, ReplayDto replay) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.sectionId = sectionId;
        this.replay = replay;
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

    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
    }

    public ReplayDto getReplay() {
        return replay;
    }

    public void setReplay(ReplayDto replay) {
        this.replay = replay;
    }
}
