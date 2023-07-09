package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class RetrieveResponseDto {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("id")
    private UUID id;

    public RetrieveResponseDto(String fullName, String email, Boolean isActive, UUID id) {
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
        this.id = id;
    }
}
