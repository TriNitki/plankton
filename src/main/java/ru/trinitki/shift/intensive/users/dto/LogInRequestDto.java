package ru.trinitki.shift.intensive.users.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LogInRequestDto {
    @NotEmpty
    @Size(min = 4, max = 32)
    private String email;
    @NotEmpty
    @Size(min = 4, max = 32)
    private String password;

    public LogInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
