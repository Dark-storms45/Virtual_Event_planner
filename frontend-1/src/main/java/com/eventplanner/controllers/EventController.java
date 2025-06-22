package com.eventplanner.controllers;

import com.eventplanner.models.Event;
import com.eventplanner.models.EventType;
import com.eventplanner.models.EventStatus;
import com.eventplanner.services.EventService;
import com.eventplanner.utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class EventController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @FXML private TextField nameField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker eventDatePicker;
    @FXML private ComboBox<EventType> eventTypeCombo;
    @FXML private TextField guestCountField;
    @FXML private TextField budgetField;
    @FXML private TextField venueField;
    @FXML private ComboBox<EventStatus> statusCombo;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private ProgressIndicator progressIndicator;

    private EventService eventService;
    private Event currentEvent;

    @Override
    protected void initializeController()
    {
        eventService = new EventService();
        progressIndicator.setVisible(false);
        
        setupUI();
        setupEventHandlers();
    }

    private void setupUI() {
        // Setup combo boxes
        eventTypeCombo.setItems(FXCollections.observableArrayList(EventType.values()));
        statusCombo.setItems(FXCollections.observableArrayList(EventStatus.values()));
        
        // Set default values
        eventTypeCombo.setValue(EventType.OTHER);
        statusCombo.setValue(EventStatus.PLANNING);
    }

    private void setupEventHandlers() {
        saveButton.setOnAction(e -> saveEvent());
        cancelButton.setOnAction(e -> cancel());
    }

    public void setEvent(Event event) {
        this.currentEvent = event;
        if (event != null) {
            populateFields(event);
        }
    }

    private void populateFields(Event event) {
        nameField.setText(event.getName());
        descriptionArea.setText(event.getDescription());
        eventDatePicker.setValue(event.getEventDate());
        eventTypeCombo.setValue(event.getEventType());
        guestCountField.setText(event.getGuestCount() != null ? event.getGuestCount().toString() : "");
        budgetField.setText(event.getBudget() != null ? event.getBudget().toString() : "");
        venueField.setText(event.getVenue());
        statusCombo.setValue(event.getStatus());
    }

    @FXML
    private void saveEvent() {
        if (!validateInput()) {
            return;
        }

        setUIEnabled(false);
        progressIndicator.setVisible(true);

        Event event = currentEvent != null ? currentEvent : new Event();
        populateEventFromFields(event);

        Task<Event> saveTask = new Task<Event>() {
            @Override
            protected Event call() throws Exception {
                if (currentEvent == null) {
                    return eventService.createEvent(event);
                } else {
                    return eventService.updateEvent(event);
                }
            }

            @Override
            protected void succeeded() {
                Event savedEvent = getValue();
                logger.info("Event saved successfully: {}", savedEvent.getName());
                
                AlertUtils.showInfo("Success", "Event saved successfully!");
                sceneManager.showDashboardScene(sceneManager.getCurrentUser());
            }

            @Override
            protected void failed() {
                logger.error("Failed to save event", getException());
                AlertUtils.showError("Save Failed", "Failed to save event. Please try again.");
                
                setUIEnabled(true);
                progressIndicator.setVisible(false);
            }
        };

        Thread saveThread = new Thread(saveTask);
        saveThread.setDaemon(true);
        saveThread.start();
    }

    private boolean validateInput() {
        if (nameField.getText().trim().isEmpty()) {
            AlertUtils.showError("Missing Information", "Please enter an event name.");
            return false;
        }

        if (eventDatePicker.getValue() == null) {
            AlertUtils.showError("Missing Information", "Please select an event date.");
            return false;
        }

        if (!guestCountField.getText().trim().isEmpty()) {
            try {
                Integer.parseInt(guestCountField.getText().trim());
            } catch (NumberFormatException e) {
                AlertUtils.showError("Invalid Input", "Guest count must be a valid number.");
                return false;
            }
        }

        if (!budgetField.getText().trim().isEmpty()) {
            try {
                new BigDecimal(budgetField.getText().trim());
            } catch (NumberFormatException e) {
                AlertUtils.showError("Invalid Input", "Budget must be a valid number.");
                return false;
            }
        }

        return true;
    }

    private void populateEventFromFields(Event event) {
        event.setName(nameField.getText().trim());
        event.setDescription(descriptionArea.getText().trim());
        event.setEventDate(eventDatePicker.getValue());
        event.setEventType(eventTypeCombo.getValue());
        event.setVenue(venueField.getText().trim());
        event.setStatus(statusCombo.getValue());

        if (!guestCountField.getText().trim().isEmpty()) {
            event.setGuestCount(Integer.parseInt(guestCountField.getText().trim()));
        }

        if (!budgetField.getText().trim().isEmpty()) {
            event.setBudget(new BigDecimal(budgetField.getText().trim()));
        }
    }

    @FXML
    private void cancel() {
        sceneManager.showDashboardScene(sceneManager.getCurrentUser());
    }

    private void setUIEnabled(boolean enabled) {
        nameField.setDisable(!enabled);
        descriptionArea.setDisable(!enabled);
        eventDatePicker.setDisable(!enabled);
        eventTypeCombo.setDisable(!enabled);
        guestCountField.setDisable(!enabled);
        budgetField.setDisable(!enabled);
        venueField.setDisable(!enabled);
        statusCombo.setDisable(!enabled);
        saveButton.setDisable(!enabled);
        cancelButton.setDisable(!enabled);
    }
}
