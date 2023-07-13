package ru.trinitki.shift.intensive.events.dto.EventGroup;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public class ReplayDto {
    @JsonProperty("replay_type")
    @NotNull
    private ReplayType replayType;
    @JsonProperty("start_date")
    @NotNull
    private LocalDate startDate;
    @JsonProperty("end_date")
    @NotNull
    private LocalDate endDate;
    private Set<LocalDate> dates;

    public ReplayDto() {
    }

    public ReplayDto(ReplayType replayType, LocalDate startDate, LocalDate endDate, Set<LocalDate> dates) {
        this.replayType = replayType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dates = dates;
    }

    public ReplayType getReplayType() {
        return replayType;
    }

    public void setReplayType(ReplayType replayType) {
        this.replayType = replayType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<LocalDate> getDates() {
        return dates;
    }

    public void setDates(Set<LocalDate> dates) {
        this.dates = dates;
    }
}
