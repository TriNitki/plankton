package ru.trinitki.shift.intensive.events.service;

import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.events.dto.Event.*;
import ru.trinitki.shift.intensive.events.repository.EventsRepository;
import ru.trinitki.shift.intensive.events.repository.entity.Events;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service("EventsService")
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public EventCreateResponseDto create(EventCreateRequestDto eventDto, String token) {
        Events event = createEvent(new Events(
                UUID.randomUUID(),
                eventDto.getDescription(),
                UUID.fromString(token),
                UUID.randomUUID(),
                eventDto.getTime(),
                eventDto.getDate()
        ));

        return new EventCreateResponseDto(event.getOwnerId(), event.getDate(), event.getTime(), event.getDescription(), event.getEventId(), event.getEventGroupId());
    }

    @Override
    public EventUpdateResponseDto update(EventUpdateRequestDto eventDto, UUID eventId, String token) {
        Events event = this.eventsRepository.findByKey_EventId(eventId);
        if (eventDto.getDescription() != null) {
            event.setDescription(eventDto.getDescription());
        }
        if (eventDto.getDate() != null) {
            event.setDate(eventDto.getDate());
        }
        if (eventDto.getTime() != null) {
            event.setTime(eventDto.getTime());
        }

        this.eventsRepository.save(event);
        return new EventUpdateResponseDto(event.getOwnerId(), event.getDate(), event.getTime(), event.getDescription(), event.getEventId(), event.getEventGroupId());
    }

    @Override
    public List<EventDto> retrieve(LocalDate startDate, LocalDate endDate, String token) {
        List<Events> events = this.eventsRepository.findAllByDateBetweenAndOwnerId(startDate, endDate, UUID.fromString(token));

        return events.stream()
                .map(event -> new EventDto(event.getEventId(), event.getEventGroupId(), event.getOwnerId(), event.getDate(), event.getTime(), event.getDescription()))
                .toList();
    }

    @Override
    public void delete(UUID eventID, String token) {
        Events event = this.eventsRepository.findByKey_EventId(eventID);
        this.eventsRepository.delete(event);
    }

    public Events createEvent(Events event) {
        this.eventsRepository.save(event);
        return event;
    }
}
