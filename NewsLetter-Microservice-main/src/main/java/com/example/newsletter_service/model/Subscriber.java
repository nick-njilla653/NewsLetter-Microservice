package com.example.newsletter_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean subscribed = true;
}