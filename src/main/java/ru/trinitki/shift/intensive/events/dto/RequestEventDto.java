package ru.trinitki.shift.intensive.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

enum Replay {
    DAILY,
    WEEKLY,
    CUSTOM
}

public class RequestEventDto {
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("time")
    private LocalTime time;
    @JsonProperty("description")
    @NotEmpty
    @Size(max = 20)
    private String description;
    @JsonProperty("replay")
    private Replay replay;

    public RequestEventDto(LocalDate date, LocalTime time, String description, Replay replay) {
        this.date = date;
        this.time = time;
        this.description = description;
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

    public Replay getReplay() {
        return replay;
    }

    public void setReplay(Replay replay) {
        this.replay = replay;
    }
}
