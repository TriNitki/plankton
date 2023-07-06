package ru.trinitki.shift.intensive.dto;

import java.sql.Time;
import java.util.Date;

public class EventIdDto extends EventDto {
    private int sectionId;

    public EventIdDto(Date date, Time time, String description, Replay replay) {
        super(date, time, description, replay);
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
}
