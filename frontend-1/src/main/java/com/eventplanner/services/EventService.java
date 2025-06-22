package com.eventplanner.services;

import com.eventplanner.config.ApiConfig;
import com.eventplanner.models.Event;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public EventService() {
        this.apiClient = ApiClient.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    public List<Event> getAllEvents() throws IOException {
        logger.info("Fetching all events");
        
        try {
            String response = apiClient.get(ApiConfig.EVENTS, String.class);
            return objectMapper.readValue(response, new TypeReference<List<Event>>() {});
        } catch (IOException e) {
            logger.error("Failed to fetch events", e);
            throw new IOException("Failed to fetch events: " + e.getMessage());
        }
    }

    public Event getEvent(Long id) throws IOException {
        logger.info("Fetching event with id: {}", id);
        
        try {
            return apiClient.get(ApiConfig.EVENTS + "/" + id, Event.class);
        } catch (IOException e) {
            logger.error("Failed to fetch event with id: {}", id, e);
            throw new IOException("Failed to fetch event: " + e.getMessage());
        }
    }

    public Event createEvent(Event event) throws IOException {
        logger.info("Creating new event: {}", event.getName());
        
        try {
            return apiClient.post(ApiConfig.EVENTS, event, Event.class);
        } catch (IOException e) {
            logger.error("Failed to create event: {}", event.getName(), e);
            throw new IOException("Failed to create event: " + e.getMessage());
        }
    }

    public Event updateEvent(Event event) throws IOException {
        logger.info("Updating event: {}", event.getName());
        
        try {
            return apiClient.put(ApiConfig.EVENTS + "/" + event.getId(), event, Event.class);
        } catch (IOException e) {
            logger.error("Failed to update event: {}", event.getName(), e);
            throw new IOException("Failed to update event: " + e.getMessage());
        }
    }

    public boolean deleteEvent(Long id) throws IOException {
        logger.info("Deleting event with id: {}", id);
        
        try {
            return apiClient.delete(ApiConfig.EVENTS + "/" + id);
        } catch (IOException e) {
            logger.error("Failed to delete event with id: {}", id, e);
            throw new IOException("Failed to delete event: " + e.getMessage());
        }
    }
}