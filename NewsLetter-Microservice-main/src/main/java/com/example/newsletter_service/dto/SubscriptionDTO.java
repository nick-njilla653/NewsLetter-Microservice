package com.example.newsletter_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Data
@Getter
@Setter
public class SubscriptionDTO {
    @Email(message = "Email should be valid")
    private String email;
}