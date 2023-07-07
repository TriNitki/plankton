package ru.trinitki.shift.intensive.events.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.events.dto.RequestEventDto;
import ru.trinitki.shift.intensive.events.dto.ResponseEventDto;
import ru.trinitki.shift.intensive.events.utils.Mocks;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/event", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Events")
public class EventController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ивент был создан")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEventDto> create(@Valid @RequestBody RequestEventDto event) {
        return ResponseEntity.ok(Mocks.event(event));
    }

}
