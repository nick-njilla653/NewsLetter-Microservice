package com.example.newsletter_service.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "newsletters")
public class NewsLetterType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime sentAt;

    private LocalDateTime updatedAt;

    private LocalDateTime scheduledFor;

    @Enumerated(EnumType.STRING)
    private NewsLetter type;
}