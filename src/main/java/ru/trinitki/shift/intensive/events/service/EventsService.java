package ru.trinitki.shift.intensive.events.service;

import ru.trinitki.shift.intensive.events.dto.EventRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventResponseDto;

public interface EventsService {
    EventResponseDto create(EventRequestDto event);
}
