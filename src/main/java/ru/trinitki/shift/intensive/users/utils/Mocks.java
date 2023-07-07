package ru.trinitki.shift.intensive.users.utils;

import ru.trinitki.shift.intensive.users.dto.*;

import java.util.UUID;

public class Mocks {
    public static SignUpResponseDto userSignIn(SignUpRequestDto userDto) {
        return new SignUpResponseDto(userDto.getFullName(), userDto.getEmail(), Boolean.TRUE, UUID.randomUUID(), "1234token");
    }

    public static LogInResponseDto userLogIn(LogInRequestDto userDto) {
        return new LogInResponseDto("Vasya Pupkin", userDto.getEmail(), Boolean.TRUE, UUID.randomUUID(), "1234token");
    }

    public static RetrieveUserDto userRetrieve(UUID id) {
        return new RetrieveUserDto("Vasya Pupkin", "vasya_pupkin@mail.ru", Boolean.TRUE, UUID.randomUUID(), "1234token");
    }
}
