package ru.trinitki.shift.intensive.events.service;

import ru.trinitki.shift.intensive.events.dto.Event.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventsService {
    EventCreateResponseDto create(EventCreateRequestDto event, String token);

    EventUpdateResponseDto update(EventUpdateRequestDto event, UUID eventId, String token);

    List<EventDto> retrieve(LocalDate startDate, LocalDate endDate, String token);

    void delete(UUID eventId, String token);
}
