package ru.trinitki.shift.intensive.events.service;

import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.events.dto.EventRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventResponseDto;
import ru.trinitki.shift.intensive.events.repository.EventsRepository;
import ru.trinitki.shift.intensive.events.repository.entity.Events;

import java.util.UUID;

@Service("EventsService")
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public EventResponseDto create(EventRequestDto eventDto) {
        Events event = new Events();

        event.setOwnerId(eventDto.getOwnerId());
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setDescription(eventDto.getDescription());
        event.setEventId(UUID.randomUUID());
        event.setEventGroupId(UUID.randomUUID());
        event.setSectionId(null);

        this.eventsRepository.save(event);
        return new EventResponseDto(event.getOwnerId(), event.getDate(), event.getTime(), event.getDescription(), event.getEventId(), event.getEventGroupId(), event.getSectionId());
    }
}
