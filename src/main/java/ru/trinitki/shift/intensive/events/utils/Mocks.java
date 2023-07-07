package ru.trinitki.shift.intensive.events.utils;

import ru.trinitki.shift.intensive.events.dto.RequestEventDto;
import ru.trinitki.shift.intensive.events.dto.ResponseEventDto;

import java.util.UUID;

public class Mocks {
    public static ResponseEventDto event(RequestEventDto eventDto) {
        return new ResponseEventDto(eventDto.getDate(), eventDto.getTime(), eventDto.getDescription(), eventDto.getReplay(), UUID.randomUUID());
    }
}
