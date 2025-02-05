package com.example.newsletter_service.dto;

import com.example.newsletter_service.model.NewsLetter;
import com.example.newsletter_service.model.NewsLetterType;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class NewsletterDTO {
    private String title;
    private String content;
    private NewsLetterType type;
    private LocalDateTime scheduledFor;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsLetterType getType() {
        return type;
    }

    public void setType(NewsLetterType type) {
        this.type = type;
    }

    public LocalDateTime getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(LocalDateTime scheduledFor) {
        this.scheduledFor = scheduledFor;
    }
}