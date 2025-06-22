package com.eventplanner.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController extends BaseController {

    @FXML private Button loginButton;
    @FXML private Button signupButton;

    @Override
    protected void initializeController() {
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        loginButton.setOnAction(e -> navigateToLogin());
        signupButton.setOnAction(e -> navigateToSignup());
        // Removed learnMoreButton code because it's null (not in FXML)
    }

    @FXML
    private void navigateToLogin() {
        sceneManager.showLoginScene();
    }

    @FXML
    private void navigateToSignup() {
        sceneManager.showSignupScene();
    }
}
