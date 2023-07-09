package ru.trinitki.shift.intensive.users.utils;

import ru.trinitki.shift.intensive.users.dto.*;

import java.util.Stack;
import java.util.UUID;

public class Mocks {
    public static SignUpResponseDto userSignIn(SignUpRequestDto userDto) {
        return new SignUpResponseDto(userDto.getFullName(), userDto.getEmail(), Boolean.TRUE, UUID.randomUUID(), "1234token");
    }

    public static LogInResponseDto userLogIn(LogInRequestDto userDto) {
        return new LogInResponseDto("1234token");
    }

    public static RetrieveResponseDto userRetrieve(UUID id) {
        return new RetrieveResponseDto("Vasya Pupkin", "vasya_pupkin@mail.ru", Boolean.TRUE, UUID.randomUUID());
    }
}
