package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SignUpResponseDto {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("access_token")
    private String accessToken;

    public SignUpResponseDto(String fullName, String email, Boolean isActive, UUID id, String accessToken) {
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
        this.id = id;
        this.accessToken = accessToken;
    }
}
