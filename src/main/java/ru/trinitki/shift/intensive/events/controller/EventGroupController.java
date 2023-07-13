package ru.trinitki.shift.intensive.events.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventGroup.EventGroupCreateResponseDto;
import ru.trinitki.shift.intensive.events.service.EventGroupsService;
import ru.trinitki.shift.intensive.events.service.EventsService;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/event-group", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Event groups")
public class EventGroupController {
    private final EventGroupsService eventGroupsService;

    @Autowired
    public EventGroupController(EventGroupsService eventGroupsService) {
        this.eventGroupsService = eventGroupsService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New event group has been created")
    })
    @PostMapping
    public ResponseEntity<EventGroupCreateResponseDto> create(@NotEmpty @RequestHeader("access-token") String token, @Valid @RequestBody EventGroupCreateRequestDto eventGroup) {
        return ResponseEntity.ok(this.eventGroupsService.create(eventGroup, token));
    }
}
