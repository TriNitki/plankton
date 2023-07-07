package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserDto {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;

    public UpdateUserDto(String fullName, String email, Boolean isActive) {
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }
}
