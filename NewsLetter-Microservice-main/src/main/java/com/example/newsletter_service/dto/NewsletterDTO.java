package com.example.newsletter_service.dto;

import com.example.newsletter_service.model.NewsLetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class NewsletterDTO {
    private String title;
    private String content;
    private NewsLetter type;
    private LocalDateTime scheduledFor;
}