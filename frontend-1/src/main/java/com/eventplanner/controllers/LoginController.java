package com.eventplanner.controllers;

import com.eventplanner.models.User;
import com.eventplanner.services.AuthService;
import com.eventplanner.utils.AlertUtils;
import com.eventplanner.utils.SceneManager;
import com.eventplanner.utils.Validator;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button signupButton;
    @FXML private ProgressIndicator progressIndicator;

    @FXML private Hyperlink forgotPasswordLink;
    @FXML private Hyperlink signupLink;
    @FXML private Button backButton;

    private AuthService authService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authService = new AuthService();
        progressIndicator.setVisible(false);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        // Check for admin quick login (bypass backend)
        if (email.equals("admin") && password.equals("1234qwer")) {
            logger.info("Admin quick login detected.");
            User adminUser = new User();
            adminUser.setEmail("admin");
            adminUser.setName("Administrator");

            SceneManager.getInstance().showDashboardScene(adminUser);
            return;
        }

        // Validate email
        if (!Validator.isValidEmail(email)) {
            AlertUtils.showError("Invalid Email", "Please enter a valid email address.");
            return;
        }

        // Validate password
        if (password.isEmpty()) {
            AlertUtils.showError("Missing Password", "Please enter your password.");
            return;
        }

        setUIEnabled(false);
        progressIndicator.setVisible(true);

        Task<User> loginTask = new Task<>() {
            @Override
            protected User call() throws Exception {
                return authService.login(email, password);
            }

            @Override
            protected void succeeded() {
                User user = getValue();
                logger.info("Login successful for user: {}", user.getEmail());
                SceneManager.getInstance().showDashboardScene(user);
            }

            @Override
            protected void failed() {
                Throwable exception = getException();
                logger.error("Login failed", exception);
                AlertUtils.showError("Login Failed", "Invalid credentials. Please check your email and password.");
                setUIEnabled(true);
                progressIndicator.setVisible(false);
            }
        };

        Thread loginThread = new Thread(loginTask);
        loginThread.setDaemon(true);
        loginThread.start();
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) {
        AlertUtils.showInfo("Forgot Password", "Password reset is not implemented yet.");
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        SceneManager.getInstance().showSignupScene();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.getInstance().showWelcomeScene();
    }

    private void setUIEnabled(boolean enabled) {
        emailField.setDisable(!enabled);
        passwordField.setDisable(!enabled);
        loginButton.setDisable(!enabled);
        signupButton.setDisable(!enabled);
        if (forgotPasswordLink != null) forgotPasswordLink.setDisable(!enabled);
        if (signupLink != null) signupLink.setDisable(!enabled);
        if (backButton != null) backButton.setDisable(!enabled);
    }
}
