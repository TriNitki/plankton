package ru.trinitki.shift.intensive.events.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.events.dto.EventGroupRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventGroupResponseDto;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/event-group", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Event groups")
public class EventGroupController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New event group has been created")
    })
    @PostMapping
    public ResponseEntity<EventGroupResponseDto> create(@RequestHeader("token") String token, @RequestBody EventGroupRequestDto eventGroup) {
        return ResponseEntity.ok(new EventGroupResponseDto(eventGroup.getDate(), eventGroup.getTime(), eventGroup.getDescription(), eventGroup.getSectionId(), eventGroup.getReplay(), UUID.randomUUID()));
    }
}
