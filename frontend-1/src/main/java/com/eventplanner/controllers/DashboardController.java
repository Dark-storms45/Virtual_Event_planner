package com.eventplanner.controllers;

import com.eventplanner.models.Event;
import com.eventplanner.models.User;
import com.eventplanner.services.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class DashboardController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @FXML private Label welcomeLabel;
    @FXML private Label totalEventsLabel;
    @FXML private Label upcomingEventsLabel;
    @FXML private Label thisMonthLabel;

    @FXML private Button createEventButton;
    @FXML private Button manageVendorsButton;
    @FXML private Button budgetTrackerButton;
    @FXML private Button logoutButton;
    @FXML private Button notificationsButton;

    @FXML private TableView<Event> eventsTable;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, LocalDate> dateColumn;
    @FXML private TableColumn<Event, String> typeColumn;
    @FXML private TableColumn<Event, String> statusColumn;

    private EventService eventService;
    private ObservableList<Event> events;

    @Override
    protected void initializeController() {
        eventService = new EventService();
        events = FXCollections.observableArrayList();

        setupUI();
        setupEventHandlers();
        loadEvents();
    }

    private void setupUI() {
        User currentUser = sceneManager.getCurrentUser();
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getName() + "!");
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        eventsTable.setItems(events);
    }

    private void setupEventHandlers() {
        createEventButton.setOnAction(e -> showCreateEventDialog());
        manageVendorsButton.setOnAction(e -> showVendorManagement());
        budgetTrackerButton.setOnAction(e -> showBudgetTracker());
        logoutButton.setOnAction(e -> logout());

        if (notificationsButton != null) {
            notificationsButton.setOnAction(e -> handleNotifications());
        }
    }

    private void loadEvents() {
        Task<List<Event>> loadEventsTask = new Task<>() {
            @Override
            protected List<Event> call() throws Exception {
                return eventService.getAllEvents();
            }

            @Override
            protected void succeeded() {
                List<Event> eventList = getValue();
                events.clear();
                events.addAll(eventList);
                updateStatistics(eventList);
            }

            @Override
            protected void failed() {
                logger.error("Failed to load events", getException());
                showError("Load Error", "Failed to load events. Please try again.");
            }
        };

        Thread thread = new Thread(loadEventsTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void updateStatistics(List<Event> eventList) {
        int total = eventList.size();
        int upcoming = 0;
        int thisMonth = 0;

        LocalDate now = LocalDate.now();
        LocalDate start = now.withDayOfMonth(1);
        LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

        for (Event event : eventList) {
            LocalDate date = event.getEventDate();
            if (date.isAfter(now)) upcoming++;
            if (!date.isBefore(start) && !date.isAfter(end)) thisMonth++;
        }

        totalEventsLabel.setText(String.valueOf(total));
        upcomingEventsLabel.setText(String.valueOf(upcoming));
        thisMonthLabel.setText(String.valueOf(thisMonth));
    }

    @FXML
    private void showCreateEventDialog() {
        sceneManager.showCreateEventScene();
    }

    @FXML
    private void showVendorManagement() {
        sceneManager.showVendorManagementScene();
    }

    @FXML
    private void showBudgetTracker() {
        sceneManager.showBudgetTrackerScene();
    }

    @FXML
    private void handleLogout() {
        logout();
    }

    @FXML
    private void showDashboard() {
    // Typically, dashboard is the current scene, so maybe just reload or do nothing
    // Or if you want to refresh data:
        loadEvents();    
    }

    @FXML
     private void showEvents(ActionEvent event) {
        System.out.println("showEvents button clicked");
        // TODO: Add logic to switch scene or display event list
    }

    @FXML
    private void showVendors(ActionEvent event) {
        System.out.println("Navigating to Vendors page...");
        // TODO: add your logic to switch to the vendors scene
}

    @FXML
    private void showBudget(ActionEvent event) {
        System.out.println("Opening Budget section...");
        // TODO: Add logic to navigate or display the budget section
}

    @FXML
    private void showReports(ActionEvent event) {
        System.out.println("Opening Reports...");
        // TODO: Add navigation or logic to show the reports section
}

@FXML
private void showSettings(ActionEvent event) {
    System.out.println("Settings clicked");
    // TODO: implement navigation to settings page
}




    @FXML
    private void logout() {
        sceneManager.logout();
    }

    @FXML
    private void handleNotifications() {
        logger.info("Notifications button clicked");
        showInfo("Notifications", "You have no new notifications at this time.");
    }
}
