package ru.trinitki.shift.intensive.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.dto.EventDto;
import ru.trinitki.shift.intensive.dto.EventIdDto;
import ru.trinitki.shift.intensive.utils.Mocks;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/event", produces = APPLICATION_JSON_VALUE)
public class EventController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ивент был создан")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventIdDto> create(@Valid @RequestBody EventDto event) {
        return ResponseEntity.ok(Mocks.event(event));
    }

}
