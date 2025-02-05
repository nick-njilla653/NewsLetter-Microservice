package com.example.newsletter_service.repository;

import com.example.newsletter_service.model.NewsLetterType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NewsletterRepository extends JpaRepository<NewsLetterType, UUID> {
    List<NewsLetterType> findByScheduledForBeforeAndSentAtIsNull(LocalDateTime now);
    List<NewsLetterType> findByScheduledForIsNotNullOrderByScheduledForAsc();
}