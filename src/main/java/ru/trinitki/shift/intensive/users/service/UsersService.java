package ru.trinitki.shift.intensive.users.service;

import ru.trinitki.shift.intensive.users.dto.*;

import java.util.UUID;

public interface UsersService {
    SignUpResponseDto create(SignUpRequestDto user);

    LogInResponseDto login(LogInRequestDto user);

    RetrieveResponseDto find(UUID id, String token);

    UpdateResponseDto update(UUID id, UpdateRequestDto updateUser, String token);
}
