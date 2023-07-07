package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignUpRequestDto {
    @NotEmpty
    @Size(min = 4, max = 32)
    @JsonProperty("full_name")
    private final String fullName;
    @NotEmpty
    @Size(min = 4, max = 32)
    private final String email;
    @NotEmpty
    @Size(min = 4, max = 32)
    private final String password;

    public SignUpRequestDto(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
