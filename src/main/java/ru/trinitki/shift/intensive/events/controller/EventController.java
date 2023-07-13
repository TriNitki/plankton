package ru.trinitki.shift.intensive.events.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.errors.controller.ErrorController;
import ru.trinitki.shift.intensive.events.dto.Event.*;
import ru.trinitki.shift.intensive.events.exception.EventIntervalBadRequestException;
import ru.trinitki.shift.intensive.events.service.EventsService;
import ru.trinitki.shift.intensive.users.exception.UserNotAuthorizedException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.plankton.event.tag.name")
public class EventController {
    private final EventsService eventsService;

    @Autowired
    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Operation(summary = "api.plankton.event.create.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.event.create.api-response.200.description"),
            @ApiResponse(responseCode = "400", description = "api.plankton.error.bad-request", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "api.plankton.error.user-unauthorized", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))})
    })
    @PostMapping(value = "/event")
    public ResponseEntity<EventCreateResponseDto> create(@NotEmpty @RequestHeader("access-token") String token, @Valid @RequestBody EventCreateRequestDto event) {
        return ResponseEntity.ok(this.eventsService.create(event, token));
    }

    @Operation(summary = "api.plankton.event.update.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.event.update.api-response.200.description"),
            @ApiResponse(responseCode = "400", description = "api.plankton.error.bad-request", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "api.plankton.error.user-unauthorized", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "403", description = "api.plankton.error.user-forbidden", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "api.plankton.event.retrieve.api-response.404.description", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))})
    })
    @PatchMapping(value = "/event/{event_id}")
    public ResponseEntity<EventUpdateResponseDto> update(@NotEmpty @RequestHeader("access-token") String token, @NotNull @PathVariable("event_id") UUID eventId, @Valid @RequestBody EventUpdateRequestDto event) {
        return ResponseEntity.ok(this.eventsService.update(event, eventId, token));
    }

    @Operation(summary = "api.plankton.events.retrieve.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.events.retrieve.api-response.200.description"),
            @ApiResponse(responseCode = "400", description = "api.plankton.error.user-forbidden", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "api.plankton.events.retrieve.api-response.401.description", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))})
    })
    @GetMapping(value = "/events")
    public ResponseEntity<List<EventDto>> retrieve(@NotEmpty @RequestHeader("access-token") String token, @RequestParam(value = "start_date", required = false) LocalDate startDate, @RequestParam(value = "end_date", required = false) LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.MIN;
        }

        if (endDate == null) {
            endDate = LocalDate.MAX;
        }

        if (startDate.isAfter(endDate)) {
            throw new EventIntervalBadRequestException();
        }

        return ResponseEntity.ok(this.eventsService.retrieve(startDate, endDate, token));
    }

    @Operation(summary = "api.plankton.event.delete.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список ивентов пользователя"),
            @ApiResponse(responseCode = "401", description = "api.plankton.error.user-unauthorized", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "403", description = "api.plankton.error.user-forbidden", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "api.plankton.event.retrieve.api-response.404.description", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content(schema = @Schema(implementation = ErrorController.ErrorResponse.class))})
    })
    @DeleteMapping(value = "/event/{event_id}")
    public ResponseEntity<Void> delete(@NotEmpty @RequestHeader("access-token") String token, @NotNull @PathVariable("event_id") UUID eventId) {
        this.eventsService.delete(eventId, token);
        return ResponseEntity.ok().build();
    }
}
