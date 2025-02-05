package com.example.newsletter_service.repository;

import com.example.newsletter_service.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
    Optional<Subscriber> findByEmail(String email);
    boolean existsByEmail(String email);
}