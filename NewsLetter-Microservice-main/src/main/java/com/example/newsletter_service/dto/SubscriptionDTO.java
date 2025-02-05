package com.example.newsletter_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Data

public class SubscriptionDTO {
    @Email(message = "Email should be valid")
    private String email;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}