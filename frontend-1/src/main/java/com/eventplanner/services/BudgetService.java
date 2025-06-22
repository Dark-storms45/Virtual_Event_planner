package com.eventplanner.services;

import com.eventplanner.config.ApiConfig;
import com.eventplanner.models.Budget;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class BudgetService {
    private static final Logger logger = LoggerFactory.getLogger(BudgetService.class);
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public BudgetService() {
        this.apiClient = ApiClient.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    public List<Budget> getBudgetsByEvent(Long eventId) throws IOException {
        logger.info("Fetching budgets for event: {}", eventId);

        try {
            String response = apiClient.get(ApiConfig.BUDGETS + "?eventId=" + eventId, String.class);
            return objectMapper.readValue(response, new TypeReference<List<Budget>>() {});
        } catch (IOException e) {
            logger.error("Failed to fetch budgets for event: {}", eventId, e);
            throw new IOException("Failed to fetch budgets: " + e.getMessage());
        }
    }

    // Alias for controller compatibility
    public List<Budget> getBudgetsByEventId(Long eventId) throws IOException {
        return getBudgetsByEvent(eventId);
    }

    public Budget createBudget(Budget budget) throws IOException {
        logger.info("Creating new budget for event: {}", budget.getEventId());

        try {
            return apiClient.post(ApiConfig.BUDGETS, budget, Budget.class);
        } catch (IOException e) {
            logger.error("Failed to create budget", e);
            throw new IOException("Failed to create budget: " + e.getMessage());
        }
    }

    public Budget updateBudget(Budget budget) throws IOException {
        logger.info("Updating budget: {}", budget.getId());

        try {
            return apiClient.put(ApiConfig.BUDGETS + "/" + budget.getId(), budget, Budget.class);
        } catch (IOException e) {
            logger.error("Failed to update budget: {}", budget.getId(), e);
            throw new IOException("Failed to update budget: " + e.getMessage());
        }
    }

    public boolean deleteBudget(Long id) throws IOException {
        logger.info("Deleting budget with id: {}", id);

        try {
            return apiClient.delete(ApiConfig.BUDGETS + "/" + id);
        } catch (IOException e) {
            logger.error("Failed to delete budget with id: {}", id, e);
            throw new IOException("Failed to delete budget: " + e.getMessage());
        }
    }
}
