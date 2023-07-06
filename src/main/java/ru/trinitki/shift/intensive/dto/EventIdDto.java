package ru.trinitki.shift.intensive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.util.Date;

public class EventIdDto extends EventDto {
    @JsonProperty("section_id")
    private int sectionId;

    public EventIdDto(Date date, Time time, String description, Replay replay, int sectionId) {
        super(date, time, description, replay);
        this.sectionId = sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
}
