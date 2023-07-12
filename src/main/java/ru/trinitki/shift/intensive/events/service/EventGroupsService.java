package ru.trinitki.shift.intensive.events.service;

import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateResponseDto;

public interface EventGroupsService {
    EventGroupCreateResponseDto create(EventGroupCreateRequestDto eventGroup, String token);
}
