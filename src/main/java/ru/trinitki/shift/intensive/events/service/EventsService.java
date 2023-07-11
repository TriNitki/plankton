package ru.trinitki.shift.intensive.events.service;

import ru.trinitki.shift.intensive.events.dto.EventRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventResponseDto;

import java.util.UUID;

public interface EventsService {
    EventResponseDto create(EventRequestDto event, UUID ownerId);
}
