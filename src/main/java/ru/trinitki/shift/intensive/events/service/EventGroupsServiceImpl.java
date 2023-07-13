package ru.trinitki.shift.intensive.events.service;

import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateResponseDto;
import ru.trinitki.shift.intensive.events.repository.EventGroupsRepository;
import ru.trinitki.shift.intensive.events.repository.EventsRepository;
import ru.trinitki.shift.intensive.events.repository.entity.EventGroups;
import ru.trinitki.shift.intensive.events.repository.entity.Events;
import ru.trinitki.shift.intensive.events.repository.entity.Replay;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@Service("EventGroupsService")
public class EventGroupsServiceImpl implements EventGroupsService {
    private final EventGroupsRepository eventGroupsRepository;
    private final EventsRepository eventsRepository;

    public EventGroupsServiceImpl(EventGroupsRepository eventGroupsRepository, EventsRepository eventsRepository) {
        this.eventGroupsRepository = eventGroupsRepository;
        this.eventsRepository = eventsRepository;
    }

    @Override
    public EventGroupCreateResponseDto create(EventGroupCreateRequestDto eventGroupDto, String token) {
        EventGroups eventGroup = new EventGroups();
        Replay eventReplay = new Replay(
                eventGroupDto.getReplay().getReplayType(),
                eventGroupDto.getReplay().getStartDate(),
                eventGroupDto.getReplay().getEndDate(),
                eventGroupDto.getReplay().getDates());

        UUID eventGroupId = UUID.randomUUID();

        eventGroup.setDate(eventGroupDto.getDate());
        eventGroup.setTime(eventGroupDto.getTime());
        eventGroup.setDescription(eventGroupDto.getDescription());
        eventGroup.setOwnerId(UUID.fromString(token));
        eventGroup.setEventGroupId(eventGroupId);
        eventGroup.setSectionId(eventGroupDto.getSectionId());
        eventGroup.setReplay(eventReplay);

        LocalDate startDate = eventReplay.getStartDate();
        LocalDate endDate = eventReplay.getEndDate();

        switch (eventReplay.getReplayType()) {
            case DAILY:
                long diffInDays = ChronoUnit.DAYS.between(startDate, endDate);
                for (int i = 0; i <= (int) diffInDays; i++) {
                    Events event = createEvent(new Events(
                            UUID.randomUUID(),
                            eventGroup.getDescription(),
                            UUID.fromString(token),
                            UUID.randomUUID(),
                            eventGroup.getTime(),
                            eventGroup.getDate().plusDays(i)
                    ));
                }
                break;
            case WEEKLY:
                long diffInWeeks = ChronoUnit.WEEKS.between(startDate, endDate);
                for (int i = 0; i <= (int) diffInWeeks; i++) {
                    Events event = createEvent(new Events(
                            UUID.randomUUID(),
                            eventGroup.getDescription(),
                            UUID.fromString(token),
                            UUID.randomUUID(),
                            eventGroup.getTime(),
                            eventGroup.getDate().plusWeeks(i)
                    ));
                }
                break;
            case MONTHLY:
                long diffInMonths = ChronoUnit.MONTHS.between(startDate, endDate);
                for (int i = 0; i <= (int) diffInMonths; i++) {
                    Events event = createEvent(new Events(
                            UUID.randomUUID(),
                            eventGroup.getDescription(),
                            UUID.fromString(token),
                            UUID.randomUUID(),
                            eventGroup.getTime(),
                            eventGroup.getDate().plusMonths(i)
                    ));
                }
                break;
            case YEARLY:
                long diffInYears = ChronoUnit.YEARS.between(startDate, endDate);
                for (int i = 0; i <= (int) diffInYears; i++) {
                    Events event = createEvent(new Events(
                            UUID.randomUUID(),
                            eventGroup.getDescription(),
                            UUID.fromString(token),
                            UUID.randomUUID(),
                            eventGroup.getTime(),
                            eventGroup.getDate().plusYears(i)
                    ));
                }
                break;
            case CUSTOM:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventGroup.getReplay().getReplayType());
        }

        this.eventGroupsRepository.save(eventGroup);

        return new EventGroupCreateResponseDto(eventGroup.getDate(), eventGroup.getTime(), eventGroup.getDescription(), eventGroup.getSectionId(), eventGroupDto.getReplay(), eventGroup.getEventGroupId());
    }

    private Events createEvent(Events event) {
        this.eventsRepository.save(event);
        return event;
    }

}
