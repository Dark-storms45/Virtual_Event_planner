package com.eventplanner.repository;

import com.eventplanner.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findByEventId(Long eventId);
    List<EventRegistration> findByUserId(Long userId);
    Optional<EventRegistration> findByUserIdAndEventId(Long userId, Long eventId);
    Optional<EventRegistration> findByUniqueId(String uniqueId);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}