package ru.trinitki.shift.intensive.events.service;

import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.events.dto.Event.*;
import ru.trinitki.shift.intensive.events.exception.EventNotFoundException;
import ru.trinitki.shift.intensive.events.repository.EventsRepository;
import ru.trinitki.shift.intensive.events.repository.entity.Events;
import ru.trinitki.shift.intensive.users.exception.UserForbiddenException;
import ru.trinitki.shift.intensive.users.exception.UserNotAuthorizedException;
import ru.trinitki.shift.intensive.users.exception.UserNotFoundException;
import ru.trinitki.shift.intensive.users.repository.UsersRepository;
import ru.trinitki.shift.intensive.users.repository.entity.Users;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service("EventsService")
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;

    public EventsServiceImpl(EventsRepository eventsRepository, UsersRepository usersRepository) {
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public EventCreateResponseDto create(EventCreateRequestDto eventDto, String token) {
        validateToken(token);

        if (usersRepository.findByKey_Id(UUID.fromString(token)) == null) {
            throw new UserNotFoundException();
        }

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
        Events event = this.eventsRepository.findByEventId(eventId);

        if (event == null) {
            throw new EventNotFoundException();
        }

        validateUser(event.getOwnerId(), token);

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
        validateToken(token);

        List<Events> events = this.eventsRepository.findAllByKey_OwnerIdAndKey_DateBetween(UUID.fromString(token), startDate, endDate);

        return events.stream()
                .map(event -> new EventDto(event.getEventId(), event.getEventGroupId(), event.getOwnerId(), event.getDate(), event.getTime(), event.getDescription()))
                .toList();
    }

    @Override
    public void delete(UUID eventID, String token) {
        Events event = this.eventsRepository.findByEventId(eventID);

        if (event == null) {
            throw new EventNotFoundException();
        }

        validateUser(event.getOwnerId(), token);

        this.eventsRepository.delete(event);
    }

    private void validateUser(UUID ownerId, String token) {
        validateToken(token);

        Users user = usersRepository.findByKey_Id(UUID.fromString(token));

        if (user == null) {
            throw new UserNotAuthorizedException();
        }

        if (!user.getId().equals(ownerId)) {
            throw new UserForbiddenException();
        }
    }

    private void validateToken(String token) {
        try {
            UUID.fromString(token);
        } catch (Exception ignore) {
            throw new UserNotAuthorizedException();
        }
    }

    public Events createEvent(Events event) {
        this.eventsRepository.save(event);
        return event;
    }
}
