package ru.trinitki.shift.intensive.utils;

import ru.trinitki.shift.intensive.dto.EventDto;
import ru.trinitki.shift.intensive.dto.EventIdDto;

public class Mocks {
    public static EventIdDto event(EventDto eventDto) {
        EventIdDto eventIdDto = new EventIdDto(eventDto.getDate(), eventDto.getTime(), eventDto.getDescription(), eventDto.getReplay());
        eventIdDto.setSectionId(5);
        return eventIdDto;
    }
}
