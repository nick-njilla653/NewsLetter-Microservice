package com.example.newsletter_service.service;


import com.example.newsletter_service.dto.NewsletterDTO;
import com.example.newsletter_service.dto.SubscriptionDTO;
import com.example.newsletter_service.model.NewsLetterType;
import com.example.newsletter_service.model.Subscriber;
import com.example.newsletter_service.repository.NewsletterRepository;
import com.example.newsletter_service.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsletterService {
    private final NewsletterRepository newsletterRepository;
    private final SubscriberRepository subscriberRepository;
    private final EmailService emailService;

    @Transactional
    public void subscribe(SubscriptionDTO dto) {
        if (subscriberRepository.existsByEmail(dto.getEmail())) {
            Subscriber subscriber = subscriberRepository.findByEmail(dto.getEmail())
                    .orElseThrow();
            subscriber.setSubscribed(true);
            subscriberRepository.save(subscriber);
        } else {
            Subscriber subscriber = new Subscriber();
            subscriber.setEmail(dto.getEmail());
            subscriberRepository.save(subscriber);
        }
    }

    @Transactional
    public void unsubscribe(String email) {
        Subscriber subscriber = subscriberRepository.findByEmail(email)
                .orElseThrow();
        subscriber.setSubscribed(false);
        subscriberRepository.save(subscriber);
    }

    public NewsLetterType createNewsletter(NewsletterDTO dto) {
        NewsLetterType newsletter = new NewsLetterType();
        newsletter.setTitle(dto.getTitle());
        newsletter.setContent(dto.getContent());
        newsletter.setType(dto.getType());
        newsletter.setScheduledFor(dto.getScheduledFor());
        return newsletterRepository.save(newsletter);
    }

    @Scheduled(fixedRate = 60000) // Vérifie toutes les minutes
    public void sendScheduledNewsletters() {
        List<NewsLetterType> newsletters = newsletterRepository
                .findByScheduledForBeforeAndSentAtIsNull(LocalDateTime.now());

        List<Subscriber> subscribers = subscriberRepository.findAll().stream()
                .filter(Subscriber::isSubscribed)
                .toList();

        for (NewsLetterType newsletter : newsletters) {
            for (Subscriber subscriber : subscribers) {
                try {
                    emailService.sendEmail(
                            subscriber.getEmail(),
                            newsletter.getTitle(),
                            newsletter.getContent()
                    );
                } catch (Exception e) {
                    // Log error
                }
            }
            newsletter.setSentAt(LocalDateTime.now());
            newsletterRepository.save(newsletter);
        }
    }

    public List<NewsLetterType> getAllNewsletters() {
        return newsletterRepository.findAll();
    }

    public NewsLetterType getNewsletter(UUID id) {
        return newsletterRepository.findById(id)
                .orElseThrow();
    }

    public List<NewsLetterType> getScheduledNewsletters() {
        return newsletterRepository.findByScheduledForIsNotNullOrderByScheduledForAsc();
    }
}