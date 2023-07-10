package ru.trinitki.shift.intensive.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trinitki.shift.intensive.users.dto.*;
import ru.trinitki.shift.intensive.users.service.UsersService;
import ru.trinitki.shift.intensive.users.utils.Mocks;

import java.util.UUID;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Tag(name = "api.plankton.user.tag.name")
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "api.plankton.user.signup.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.user.signup.api-response.200.description"),
            @ApiResponse(responseCode = "400", description = "api.plankton.user.signup.api-response.400.description", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content})
    })
    @PostMapping(value = "/signup")
    public ResponseEntity<SignUpResponseDto> signup(@Valid @RequestBody SignUpRequestDto user) {
        return ResponseEntity.ok(this.usersService.create(user));
    }

    @Operation(summary = "api.plankton.user.login.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.user.login.api-response.200.description"),
            @ApiResponse(responseCode = "400", description = "api.plankton.user.login.api-response.400.description", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "api.plankton.user.login.api-response.401.description", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content})
    })
    @PostMapping(value = "/login")
    public ResponseEntity<LogInResponseDto> login(@Valid @RequestBody LogInRequestDto user) {
        return ResponseEntity.ok(Mocks.userLogIn(user));
    }

    @Operation(summary = "api.plankton.user.retrieve.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.user.retrieve.api-response.200.description"),
            @ApiResponse(responseCode = "401", description = "api.plankton.error.user-unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "api.plankton.error.user-forbidden", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "api.plankton.user.retrieve.api-response.404.description", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content})
    })
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<RetrieveResponseDto> retrieve(@PathVariable UUID id) {
        return ResponseEntity.ok(this.usersService.find(id));
    }

    @Operation(summary = "api.plankton.user.update.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.user.update.api-response.200.description"),
            @ApiResponse(responseCode = "400", description = "api.plankton.user.update.api-response.400.description", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "api.plankton.error.user-unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "api.plankton.error.user-forbidden", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "api.plankton.user.update.api-response.404.description", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content})
    })
    @PatchMapping(value = "/user/{id}")
    public ResponseEntity<UpdateResponseDto> update(@PathVariable UUID id, @Valid @RequestBody UpdateRequestDto user) {
        return ResponseEntity.ok(this.usersService.update(id, user));
    }

    @Operation(summary = "api.plankton.user.delete.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.plankton.user.delete.api-response.200.description"),
            @ApiResponse(responseCode = "401", description = "api.plankton.error.user-unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "api.plankton.error.user-forbidden", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "api.plankton.user.delete.api-response.404.description", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "api.plankton.error.server", content = {@Content})
    })
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        UpdateRequestDto user = new UpdateRequestDto();
        user.setActive(Boolean.FALSE);
        this.usersService.update(id, user);
        return ResponseEntity.ok().build();
    }
}
