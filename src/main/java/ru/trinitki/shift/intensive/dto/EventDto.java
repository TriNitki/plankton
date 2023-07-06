package ru.trinitki.shift.intensive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.sql.Time;
import java.util.Date;

enum Replay {
    DAILY,
    WEEKLY,
    CUSTOM
}

public class EventDto {
    @JsonProperty("date")
    private Date date;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("description")
    @NotEmpty
    @Size(max = 20)
    private String description;
    @JsonProperty("replay")
    private Replay replay;

    public EventDto(Date date, Time time, String description, Replay replay) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.replay = replay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
