package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogInResponseDto {
    @JsonProperty("access_token")
    private String accessToken;

    public LogInResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
