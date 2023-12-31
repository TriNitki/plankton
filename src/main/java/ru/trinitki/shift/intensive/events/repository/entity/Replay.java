package ru.trinitki.shift.intensive.events.repository.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import ru.trinitki.shift.intensive.events.dto.EventGroup.ReplayType;

import java.time.LocalDate;
import java.util.Set;

@UserDefinedType("replay")
public class Replay {
    @Column("replay_type")
    private ReplayType replayType;
    @Column("start_date")
    private LocalDate startDate;
    @Column("end_date")
    private LocalDate endDate;
    private Set<LocalDate> dates;

    public Replay() {
    }

    public Replay(ReplayType replayType, LocalDate startDate, LocalDate endDate, Set<LocalDate> dates) {
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
