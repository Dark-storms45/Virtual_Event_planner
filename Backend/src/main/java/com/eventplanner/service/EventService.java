package com.eventplanner.service;

import com.eventplanner.model.Event;
import com.eventplanner.model.EventRegistration;
import com.eventplanner.model.User;
import com.eventplanner.repository.EventRepository;
import com.eventplanner.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository registrationRepository;

    @Autowired
    private GoogleMeetService googleMeetService;

    @Autowired
    private EmailService emailService;

    public Event createEvent(Event event) {
        if (event.isOnline()) {
            String meetLink = googleMeetService.generateMeetLink(event.getName());
            event.setGoogleMeetLink(meetLink);
        }
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByCreator(Long creatorId) {
        return eventRepository.findByCreatorId(creatorId);
    }

    public List<Event> searchEvents(String keyword) {
        return eventRepository.searchEvents(keyword);
    }

    @Transactional
    public Event updateEvent(Long eventId, Event updatedEvent, User currentUser) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (!optionalEvent.isPresent()) {
            throw new Exception("Event not found");
        }

        Event existingEvent = optionalEvent.get();
        if (!existingEvent.getCreator().getId().equals(currentUser.getId())) {
            throw new Exception("You are not authorized to update this event");
        }

        // Update event fields
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setEventType(updatedEvent.getEventType());
        existingEvent.setVenue(updatedEvent.getVenue());
        existingEvent.setOnline(updatedEvent.isOnline());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());

        // Update Google Meet link if event is online
        if (updatedEvent.isOnline()) {
            if (existingEvent.getGoogleMeetLink() == null) {
                String meetLink = googleMeetService.generateMeetLink(updatedEvent.getName());
                existingEvent.setGoogleMeetLink(meetLink);
            }
        } else {
            existingEvent.setGoogleMeetLink(null);
        }

        Event savedEvent = eventRepository.save(existingEvent);

        // Send update notifications
        emailService.sendEventUpdateNotification(savedEvent, "UPDATED");

        return savedEvent;
    }

    @Transactional
    public void deleteEvent(Long eventId, User currentUser) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (!optionalEvent.isPresent()) {
            throw new Exception("Event not found");
        }

        Event event = optionalEvent.get();
        if (!event.getCreator().getId().equals(currentUser.getId())) {
            throw new Exception("You are not authorized to delete this event");
        }

        // Send deletion notifications before deleting
        emailService.sendEventUpdateNotification(event, "DELETED");

        eventRepository.delete(event);
    }

    @Transactional
    public EventRegistration registerForEvent(Long eventId, User user) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (!optionalEvent.isPresent()) {
            throw new Exception("Event not found");
        }

        Event event = optionalEvent.get();

        // Check if user is already registered
        if (registrationRepository.existsByUserIdAndEventId(user.getId(), eventId)) {
            throw new Exception("You are already registered for this event");
        }

        EventRegistration registration = new EventRegistration(user, event);
        EventRegistration savedRegistration = registrationRepository.save(registration);

        // Send confirmation email
        emailService.sendRegistrationConfirmation(savedRegistration);

        return savedRegistration;
    }

    public List<EventRegistration> getEventRegistrations(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public List<EventRegistration> getUserRegistrations(Long userId) {
        return registrationRepository.findByUserId(userId);
    }

    public Optional<EventRegistration> getRegistrationByUniqueId(String uniqueId) {
        return registrationRepository.findByUniqueId(uniqueId);
    }
}