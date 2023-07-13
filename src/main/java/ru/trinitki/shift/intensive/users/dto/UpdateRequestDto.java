package ru.trinitki.shift.intensive.users.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotEmpty;

public class UpdateRequestDto {
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;

    public UpdateRequestDto() {
    }

    public UpdateRequestDto(String fullName, String email, Boolean isActive) {
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("is_active")
    public Boolean getActive() {
        return isActive;
    }

    @JsonSetter("is_active")
    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UpdateUserDto{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
