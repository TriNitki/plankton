package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UpdateResponseDto {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("id")
    private UUID id;

    public UpdateResponseDto(String fullName, String email, Boolean isActive, UUID id) {
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
        this.id = id;
    }
}
