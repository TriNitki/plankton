package ru.trinitki.shift.intensive.events.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.events.dto.Event.*;
import ru.trinitki.shift.intensive.events.service.EventsService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
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
    @PostMapping(value = "/event")
    public ResponseEntity<EventCreateResponseDto> create(@RequestHeader("access-token") String token, @Valid @RequestBody EventCreateRequestDto event) {
        return ResponseEntity.ok(this.eventsService.create(event, token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ивент был изменен")
    })
    @PatchMapping(value = "/event/{event_id}")
    public ResponseEntity<EventUpdateResponseDto> update(@RequestHeader("access-token") String token, @PathVariable("event_id") UUID eventId, @RequestBody EventUpdateRequestDto event) {
        return ResponseEntity.ok(this.eventsService.update(event, eventId, token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список ивентов пользователя")
    })
    @GetMapping(value = "/events")
    public ResponseEntity<List<EventDto>> retrieve(@RequestHeader("access-token") String token, @RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
        return ResponseEntity.ok(this.eventsService.retrieve(startDate, endDate, token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список ивентов пользователя")
    })
    @DeleteMapping(value = "/event/{event_id}")
    public ResponseEntity<Void> delete(@RequestHeader("access-token") String token, @PathVariable("event_id") UUID eventId) {
        this.eventsService.delete(eventId, token);
        return ResponseEntity.ok().build();
    }
}
