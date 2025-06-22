package com.eventplanner.controllers;

import com.eventplanner.models.Budget;
import com.eventplanner.models.Event;
import com.eventplanner.services.BudgetService;
import com.eventplanner.services.EventService;
import com.eventplanner.utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class BudgetController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

    @FXML private ComboBox<Event> eventCombo;
    @FXML private TextField categoryField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField allocatedAmountField;
    @FXML private TextField spentAmountField;
    @FXML private Button saveButton;
    @FXML private Button clearButton;
    @FXML private Button deleteButton;
    @FXML private TableView<Budget> budgetTable;
    @FXML private TableColumn<Budget, String> categoryColumn;
    @FXML private TableColumn<Budget, BigDecimal> allocatedColumn;
    @FXML private TableColumn<Budget, BigDecimal> spentColumn;
    @FXML private TableColumn<Budget, BigDecimal> remainingColumn;
    @FXML private Label totalBudgetLabel;
    @FXML private Label totalSpentLabel;
    @FXML private Label totalRemainingLabel;

    private BudgetService budgetService;
    private EventService eventService;
    private ObservableList<Budget> budgets;
    private ObservableList<Event> events;
    private Budget selectedBudget;

    @Override
    protected void initializeController() {
        budgetService = new BudgetService();
        eventService = new EventService();
        budgets = FXCollections.observableArrayList();
        events = FXCollections.observableArrayList();
        
        setupUI();
        setupEventHandlers();
        loadEvents();
    }

    private void setupUI() {
        // Setup table columns
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        allocatedColumn.setCellValueFactory(new PropertyValueFactory<>("allocatedAmount"));
        spentColumn.setCellValueFactory(new PropertyValueFactory<>("spentAmount"));
        remainingColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getRemainingAmount()));
        
        budgetTable.setItems(budgets);
        eventCombo.setItems(events);
        deleteButton.setDisable(true);
    }

    private void setupEventHandlers() {
        saveButton.setOnAction(e -> saveBudget());
        clearButton.setOnAction(e -> clearFields());
        deleteButton.setOnAction(e -> deleteBudget());
        
        eventCombo.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldEvent, newEvent) -> {
                if (newEvent != null) {
                    loadBudgetsForEvent(newEvent.getId());
                }
            }
        );
        
        budgetTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    selectedBudget = newSelection;
                    populateFields(newSelection);
                    deleteButton.setDisable(false);
                } else {
                    selectedBudget = null;
                    deleteButton.setDisable(true);
                }
            }
        );
    }

    private void loadEvents() {
        Task<List<Event>> loadTask = new Task<List<Event>>() {
            @Override
            protected List<Event> call() throws Exception {
                return eventService.getAllEvents();
            }

            @Override
            protected void succeeded() {
                events.clear();
                events.addAll(getValue());
            }

            @Override
            protected void failed() {
                logger.error("Failed to load events", getException());
                AlertUtils.showError("Load Error", "Failed to load events.");
            }
        };

        Thread loadThread = new Thread(loadTask);
        loadThread.setDaemon(true);
        loadThread.start();
    }

    private void loadBudgetsForEvent(Long eventId) {
        Task<List<Budget>> loadTask = new Task<List<Budget>>() {
            @Override
            protected List<Budget> call() throws Exception {
                return budgetService.getBudgetsByEventId(eventId);
            }

            @Override
            protected void succeeded() {
                budgets.clear();
                budgets.addAll(getValue());
                updateTotals();
            }

            @Override
            protected void failed() {
                logger.error("Failed to load budgets", getException());
                AlertUtils.showError("Load Error", "Failed to load budgets.");
            }
        };

        Thread loadThread = new Thread(loadTask);
        loadThread.setDaemon(true);
        loadThread.start();
    }

    private void populateFields(Budget budget) {
        categoryField.setText(budget.getCategory());
        descriptionArea.setText(budget.getDescription());
        allocatedAmountField.setText(budget.getAllocatedAmount().toString());
        spentAmountField.setText(budget.getSpentAmount().toString());
    }

    @FXML
    private void saveBudget() {
        if (!validateInput()) return;

        Event selectedEvent = eventCombo.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            AlertUtils.showError("Missing Information", "Please select an event.");
            return;
        }

        Budget budget = selectedBudget != null ? selectedBudget : new Budget();
        populateBudgetFromFields(budget);
        budget.setEventId(selectedEvent.getId());

        Task<Budget> saveTask = new Task<Budget>() {
            @Override
            protected Budget call() throws Exception {
                if (selectedBudget == null) {
                    return budgetService.createBudget(budget);
                } else {
                    return budgetService.updateBudget(budget);
                }
            }

            @Override
            protected void succeeded() {
                AlertUtils.showInfo("Success", "Budget saved successfully!");
                loadBudgetsForEvent(selectedEvent.getId());
                clearFields();
            }

            @Override
            protected void failed() {
                logger.error("Failed to save budget", getException());
                AlertUtils.showError("Save Failed", "Failed to save budget.");
            }
        };

        Thread saveThread = new Thread(saveTask);
        saveThread.setDaemon(true);
        saveThread.start();
    }

    @FXML
    private void deleteBudget() {
        if (selectedBudget == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Budget");
        alert.setHeaderText("Are you sure you want to delete this budget item?");
        alert.setContentText(selectedBudget.getCategory());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Task<Boolean> deleteTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return budgetService.deleteBudget(selectedBudget.getId());
                    }

                    @Override
                    protected void succeeded() {
                        AlertUtils.showInfo("Success", "Budget deleted successfully!");
                        Event selectedEvent = eventCombo.getSelectionModel().getSelectedItem();
                        if (selectedEvent != null) {
                            loadBudgetsForEvent(selectedEvent.getId());
                        }
                        clearFields();
                    }

                    @Override
                    protected void failed() {
                        logger.error("Failed to delete budget", getException());
                        AlertUtils.showError("Delete Failed", "Failed to delete budget.");
                    }
                };

                Thread deleteThread = new Thread(deleteTask);
                deleteThread.setDaemon(true);
                deleteThread.start();
            }
        });
    }

    @FXML
    private void clearFields() {
        categoryField.clear();
        descriptionArea.clear();
        allocatedAmountField.clear();
        spentAmountField.clear();
        
        selectedBudget = null;
        budgetTable.getSelectionModel().clearSelection();
        deleteButton.setDisable(true);
    }

    private boolean validateInput() {
        if (categoryField.getText().trim().isEmpty()) {
            AlertUtils.showError("Missing Information", "Please enter budget category.");
            return false;
        }

        try {
            new BigDecimal(allocatedAmountField.getText().trim());
        } catch (NumberFormatException e) {
            AlertUtils.showError("Invalid Input", "Allocated amount must be a valid number.");
            return false;
        }

        try {
            new BigDecimal(spentAmountField.getText().trim());
        } catch (NumberFormatException e) {
            AlertUtils.showError("Invalid Input", "Spent amount must be a valid number.");
            return false;
        }

        return true;
    }

    private void populateBudgetFromFields(Budget budget) {
        budget.setCategory(categoryField.getText().trim());
        budget.setDescription(descriptionArea.getText().trim());
        budget.setAllocatedAmount(new BigDecimal(allocatedAmountField.getText().trim()));
        budget.setSpentAmount(new BigDecimal(spentAmountField.getText().trim()));
    }

    private void updateTotals() {
        BigDecimal totalBudget = BigDecimal.ZERO;
        BigDecimal totalSpent = BigDecimal.ZERO;

        for (Budget budget : budgets) {
            totalBudget = totalBudget.add(budget.getAllocatedAmount());
            totalSpent = totalSpent.add(budget.getSpentAmount());
        }

        BigDecimal totalRemaining = totalBudget.subtract(totalSpent);

        totalBudgetLabel.setText("$" + totalBudget.toString());
        totalSpentLabel.setText("$" + totalSpent.toString());
        totalRemainingLabel.setText("$" + totalRemaining.toString());
    }
}