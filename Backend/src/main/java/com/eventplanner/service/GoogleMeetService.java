package com.eventplanner.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GoogleMeetService {

    public String generateMeetLink(String eventName) {
        // In a real implementation, you would integrate with Google Calendar API
        // For now, we'll generate a mock meet link
        String meetId = UUID.randomUUID().toString().substring(0, 10);
        return "https://meet.google.com/" + meetId;
    }
}