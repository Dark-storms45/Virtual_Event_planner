package com.eventplanner.service;

import com.eventplanner.model.Event;
import com.eventplanner.model.EventRegistration;
import com.eventplanner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from}")
    private String fromEmail;

    public void sendRegistrationConfirmation(EventRegistration registration) {
        if (registration.getUser().isAnonymous()) {
            return; // Don't send emails to anonymous users
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(registration.getUser().getEmail());
        message.setSubject("Event Registration Confirmation - " + registration.getEvent().getName());

        String emailBody = buildRegistrationEmailBody(registration);
        message.setText(emailBody);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            // Log error in production
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    public void sendEventUpdateNotification(Event event, String changeType) {
        List<EventRegistration> registrations = event.getRegistrations();

        for (EventRegistration registration : registrations) {
            if (registration.getUser().isAnonymous()) {
                continue; // Skip anonymous users
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(registration.getUser().getEmail());
            message.setSubject("Event Update - " + event.getName());

            String emailBody = buildUpdateEmailBody(event, changeType, registration);
            message.setText(emailBody);

            try {
                mailSender.send(message);
            } catch (Exception e) {
                // Log error in production
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }
    }

    private String buildRegistrationEmailBody(EventRegistration registration) {
        Event event = registration.getEvent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        StringBuilder body = new StringBuilder();
        body.append("Dear ").append(registration.getUser().getName()).append(",\n\n");
        body.append("Thank you for registering for the event: ").append(event.getName()).append("\n\n");
        body.append("Event Details:\n");
        body.append("Event Type: ").append(event.getEventType()).append("\n");
        body.append("Description: ").append(event.getDescription()).append("\n");
        body.append("Start Time: ").append(event.getStartTime().format(formatter)).append("\n");
        body.append("End Time: ").append(event.getEndTime().format(formatter)).append("\n");

        if (event.isOnline()) {
            body.append("Event Type: Online\n");
            if (event.getGoogleMeetLink() != null) {
                body.append("Meeting Link: ").append(event.getGoogleMeetLink()).append("\n");
            }
        } else {
            body.append("Venue: ").append(event.getVenue()).append("\n");
        }

        body.append("\nYour Registration Details:\n");
        body.append("Registration ID: ").append(registration.getUniqueId()).append("\n");
        body.append("Registered At: ").append(registration.getRegisteredAt().format(formatter)).append("\n\n");
        body.append("Please keep this Registration ID for your records. You will need it to access the event.\n\n");
        body.append("Best regards,\n");
        body.append("Virtual Event Planner Team");

        return body.toString();
    }

    private String buildUpdateEmailBody(Event event, String changeType, EventRegistration registration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        StringBuilder body = new StringBuilder();
        body.append("Dear ").append(registration.getUser().getName()).append(",\n\n");
        body.append("We are writing to inform you about an update to the event you registered for.\n\n");
        body.append("Event: ").append(event.getName()).append("\n");
        body.append("Change Type: ").append(changeType).append("\n\n");

        body.append("Updated Event Details:\n");
        body.append("Event Type: ").append(event.getEventType()).append("\n");
        body.append("Description: ").append(event.getDescription()).append("\n");
        body.append("Start Time: ").append(event.getStartTime().format(formatter)).append("\n");
        body.append("End Time: ").append(event.getEndTime().format(formatter)).append("\n");

        if (event.isOnline()) {
            body.append("Event Type: Online\n");
            if (event.getGoogleMeetLink() != null) {
                body.append("Meeting Link: ").append(event.getGoogleMeetLink()).append("\n");
            }
        } else {
            body.append("Venue: ").append(event.getVenue()).append("\n");
        }

        body.append("\nYour Registration ID: ").append(registration.getUniqueId()).append("\n\n");

        if ("DELETED".equals(changeType)) {
            body.append("Unfortunately, this event has been cancelled. We apologize for any inconvenience.\n\n");
        } else {
            body.append("Please make note of these changes. Your registration remains valid.\n\n");
        }

        body.append("Best regards,\n");
        body.append("Virtual Event Planner Team");

        return body.toString();
    }
}