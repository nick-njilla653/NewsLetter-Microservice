package com.example.newsletter_service.controller;

import com.example.newsletter_service.dto.NewsletterDTO;
import com.example.newsletter_service.dto.SubscriptionDTO;
import com.example.newsletter_service.model.NewsLetterType;
import com.example.newsletter_service.service.NewsletterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/newsletters")
@RequiredArgsConstructor
@Tag(name = "Newsletter API", description = "APIs pour gérer les newsletters et les abonnements")
public class NewsletterController {

    private final NewsletterService newsletterService;

    @PostMapping("/subscribe")
    @Operation(summary = "S'abonner à la newsletter")
    public ResponseEntity<Void> subscribe(@Valid @RequestBody SubscriptionDTO dto) {
        newsletterService.subscribe(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unsubscribe")
    @Operation(summary = "Se désabonner de la newsletter")
    public ResponseEntity<Void> unsubscribe(@Valid @RequestBody SubscriptionDTO dto) {
        newsletterService.unsubscribe(dto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle newsletter")
    public ResponseEntity<NewsLetterType> createNewsletter(@Valid @RequestBody NewsletterDTO dto) {
        NewsLetterType newsletter = newsletterService.createNewsletter(dto);
        return ResponseEntity.ok(newsletter);
    }

    @GetMapping
    @Operation(summary = "Obtenir toutes les newsletters")
    public ResponseEntity<List<NewsLetterType>> getAllNewsletters() {
        return ResponseEntity.ok(newsletterService.getAllNewsletters());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une newsletter spécifique")
    public ResponseEntity<NewsLetterType> getNewsletter(@PathVariable UUID id) {
        return ResponseEntity.ok(newsletterService.getNewsletter(id));
    }

    @GetMapping("/scheduled")
    @Operation(summary = "Obtenir les newsletters programmées")
    public ResponseEntity<List<NewsLetterType>> getScheduledNewsletters() {
        return ResponseEntity.ok(newsletterService.getScheduledNewsletters());
    }
}