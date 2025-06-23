package com.eventplanner.controller;

import com.eventplanner.config.JwtUtil;
import com.eventplanner.dto.ApiResponse;
import com.eventplanner.dto.EventRequest;
import com.eventplanner.model.Event;
import com.eventplanner.model.EventRegistration;
import com.eventplanner.model.User;
import com.eventplanner.service.EventService;
import com.eventplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createEvent(
            @Valid @RequestBody EventRequest eventRequest,
            @RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            Event event = new Event(
                    eventRequest.getName(),
                    eventRequest.getDescription(),
                    eventRequest.getEventType(),
                    eventRequest.getVenue(),
                    eventRequest.isOnline(),
                    eventRequest.getStartTime(),
                    eventRequest.getEndTime(),
                    currentUser
            );

            Event createdEvent = eventService.createEvent(event);

            return ResponseEntity.ok(new ApiResponse(true, "Event created successfully!", createdEvent));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to create event: " + e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            return ResponseEntity.ok(new ApiResponse(true, "Events retrieved successfully!", events));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to retrieve events: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEventById(@PathVariable Long id) {
        try {
            Optional<Event> event = eventService.getEventById(id);
            if (!event.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Event not found"));
            }

            return ResponseEntity.ok(new ApiResponse(true, "Event retrieved successfully!", event.get()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to retrieve event: " + e.getMessage()));
        }
    }

    @GetMapping("/my-events")
    public ResponseEntity<ApiResponse> getMyEvents(@RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            List<Event> events = eventService.getEventsByCreator(currentUser.getId());
            return ResponseEntity.ok(new ApiResponse(true, "Your events retrieved successfully!", events));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to retrieve events: " + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchEvents(@RequestParam String keyword) {
        try {
            List<Event> events = eventService.searchEvents(keyword);
            return ResponseEntity.ok(new ApiResponse(true, "Search completed successfully!", events));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Search failed: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest eventRequest,
            @RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            Event updatedEvent = new Event(
                    eventRequest.getName(),
                    eventRequest.getDescription(),
                    eventRequest.getEventType(),
                    eventRequest.getVenue(),
                    eventRequest.isOnline(),
                    eventRequest.getStartTime(),
                    eventRequest.getEndTime(),
                    currentUser
            );

            Event event = eventService.updateEvent(id, updatedEvent, currentUser);
            return ResponseEntity.ok(new ApiResponse(true, "Event updated successfully!", event));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to update event: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEvent(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            eventService.deleteEvent(id, currentUser);
            return ResponseEntity.ok(new ApiResponse(true, "Event deleted successfully!"));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to delete event: " + e.getMessage()));
        }
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<ApiResponse> registerForEvent(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            EventRegistration registration = eventService.registerForEvent(id, currentUser);
            return ResponseEntity.ok(new ApiResponse(true, "Registered for event successfully!", registration));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Registration failed: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}/registrations")
    public ResponseEntity<ApiResponse> getEventRegistrations(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            List<EventRegistration> registrations = eventService.getEventRegistrations(id);
            return ResponseEntity.ok(new ApiResponse(true, "Registrations retrieved successfully!", registrations));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to retrieve registrations: " + e.getMessage()));
        }
    }

    @GetMapping("/my-registrations")
    public ResponseEntity<ApiResponse> getMyRegistrations(@RequestHeader("Authorization") String token) {
        try {
            User currentUser = getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid token"));
            }

            List<EventRegistration> registrations = eventService.getUserRegistrations(currentUser.getId());
            return ResponseEntity.ok(new ApiResponse(true, "Your registrations retrieved successfully!", registrations));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to retrieve registrations: " + e.getMessage()));
        }
    }

    @GetMapping("/registration/{uniqueId}")
    public ResponseEntity<ApiResponse> getRegistrationByUniqueId(@PathVariable String uniqueId) {
        try {
            Optional<EventRegistration> registration = eventService.getRegistrationByUniqueId(uniqueId);
            if (!registration.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Registration not found"));
            }

            return ResponseEntity.ok(new ApiResponse(true, "Registration retrieved successfully!", registration.get()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Failed to retrieve registration: " + e.getMessage()));
        }
    }

    private User getCurrentUser(String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return null;
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.isTokenValid(token)) {
                return null;
            }

            String email = jwtUtil.getEmailFromToken(token);

            if (jwtUtil.isAnonymousToken(token)) {
                // For anonymous users, create a temporary user object
                User anonymousUser = new User();
                anonymousUser.setId(Long.parseLong(email.split("_")[1]));
                anonymousUser.setEmail(email);
                anonymousUser.setName("Anonymous User");
                anonymousUser.setAnonymous(true);
                return anonymousUser;
            }

            Optional<User> user = userService.findByEmail(email);
            return user.orElse(null);

        } catch (Exception e) {
            return null;
        }
    }
}