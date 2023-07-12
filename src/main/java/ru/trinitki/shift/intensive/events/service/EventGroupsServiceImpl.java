package ru.trinitki.shift.intensive.events.service;

import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateResponseDto;
import ru.trinitki.shift.intensive.events.repository.EventGroupsRepository;
import ru.trinitki.shift.intensive.events.repository.entity.EventGroups;
import ru.trinitki.shift.intensive.events.repository.entity.Replay;

import java.util.UUID;

@Service("EventGroupsService")
public class EventGroupsServiceImpl implements EventGroupsService {
    private final EventGroupsRepository eventGroupsRepository;

    public EventGroupsServiceImpl(EventGroupsRepository eventGroupsRepository) {
        this.eventGroupsRepository = eventGroupsRepository;
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

        this.eventGroupsRepository.save(eventGroup);

        return new EventGroupCreateResponseDto(eventGroup.getDate(), eventGroup.getTime(), eventGroup.getDescription(), eventGroup.getSectionId(), eventGroupDto.getReplay(), eventGroup.getEventGroupId());
    }
}
