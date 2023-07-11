package ru.trinitki.shift.intensive.events.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.events.dto.EventRequestDto;
import ru.trinitki.shift.intensive.events.dto.EventResponseDto;
import ru.trinitki.shift.intensive.events.service.EventsService;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/event", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Events")
public class EventController {
    private final EventsService eventsService;

    @Autowired
    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ивент был создан")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResponseDto> create(@RequestHeader("token") String token, @Valid @RequestBody EventRequestDto event) {
        return ResponseEntity.ok(this.eventsService.create(event, UUID.fromString(token)));
    }

}
