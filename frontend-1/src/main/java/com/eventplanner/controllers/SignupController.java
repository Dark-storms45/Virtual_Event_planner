package com.eventplanner.controllers;

import com.eventplanner.models.User;
import com.eventplanner.services.AuthService;
import com.eventplanner.utils.AlertUtils;
import com.eventplanner.utils.Validator;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    @FXML private Button signupButton;
    @FXML private Button backButton;
    @FXML private Hyperlink loginLink;
    @FXML private Label errorMessage;
    @FXML private Label successMessage;
    @FXML private ProgressIndicator progressIndicator;

    private AuthService authService;

    @Override
    protected void initializeController() {
        authService = new AuthService();
        if (progressIndicator != null) progressIndicator.setVisible(false);
        if (errorMessage != null) errorMessage.setVisible(false);
        if (successMessage != null) successMessage.setVisible(false);
    }

    @FXML
    private void handleSignup() {
        clearMessages();

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String fullName = firstName + " " + lastName;
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();  // not used, but kept for UI
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Input validation
        if (firstName.isEmpty() || lastName.isEmpty()) {
            showError("Please enter both first and last name.");
            return;
        }

        if (!Validator.isValidEmail(email)) {
            showError("Please enter a valid email address.");
            return;
        }

        if (!Validator.isValidPassword(password)) {
            showError("Password must be at least 8 characters long and contain letters and numbers.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        setUIEnabled(false);
        if (progressIndicator != null) progressIndicator.setVisible(true);

        Task<User> signupTask = new Task<>() {
            @Override
            protected User call() throws Exception {
                return authService.register(fullName, email, password);
            }

            @Override
            protected void succeeded() {
                User user = getValue();
                logger.info("Signup successful for user: {}", user.getEmail());

                if (successMessage != null) {
                    successMessage.setText("Registration successful! Redirecting...");
                    successMessage.setVisible(true);
                } else {
                    AlertUtils.showInfo("Registration Successful", "Welcome to Event Planner!");
                }

                sceneManager.showDashboardScene(user);
            }

            @Override
            protected void failed() {
                logger.error("Signup failed", getException());
                showError("Could not create account. Please try again.");
                setUIEnabled(true);
                if (progressIndicator != null) progressIndicator.setVisible(false);
            }
        };

        Thread thread = new Thread(signupTask);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleLogin() {
        sceneManager.showLoginScene();
    }

    @FXML
    private void handleBack() {
        sceneManager.showWelcomeScene();
    }

    private void setUIEnabled(boolean enabled) {
        firstNameField.setDisable(!enabled);
        lastNameField.setDisable(!enabled);
        emailField.setDisable(!enabled);
        phoneField.setDisable(!enabled);
        passwordField.setDisable(!enabled);
        confirmPasswordField.setDisable(!enabled);
        signupButton.setDisable(!enabled);
        loginLink.setDisable(!enabled);
        backButton.setDisable(!enabled);
    }

    private void showError(String message) {
        if (errorMessage != null) {
            errorMessage.setText(message);
            errorMessage.setVisible(true);
        } else {
            AlertUtils.showError("Signup Error", message);
        }
    }

    private void clearMessages() {
        if (errorMessage != null) errorMessage.setVisible(false);
        if (successMessage != null) successMessage.setVisible(false);
    }
}
