package com.eventplanner.utils;

import com.eventplanner.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SceneManager {
    private static final Logger logger = LoggerFactory.getLogger(SceneManager.class);
    private static SceneManager instance;

    private Stage primaryStage;
    private User currentUser;

    // Private constructor to enforce singleton pattern
    private SceneManager() {}

    // Thread-safe singleton getter
    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Scene navigation methods
    public void showWelcomeScene() {
        loadScene("/fxml/welcome.fxml", "Welcome - EventPro");
    }

    public void showLoginScene() {
        loadScene("/fxml/login.fxml", "Login - EventPro");
    }

    public void showSignupScene() {
        loadScene("/fxml/signup.fxml", "Sign Up - EventPro");
    }

    public void showDashboardScene(User user) {
        this.currentUser = user;
        loadScene("/fxml/dashboard.fxml", "Dashboard - EventPro");
    }

    public void showCreateEventScene() {
        loadScene("/fxml/event-create.fxml", "Create Event - EventPro");
    }

    public void showVendorManagementScene() {
        loadScene("/fxml/vendor-management.fxml", "Vendors - EventPro");
    }

    public void showBudgetTrackerScene() {
        loadScene("/fxml/budget-tracker.fxml", "Budget Tracker - EventPro");
    }

    /**
     * Loads an FXML scene and sets it on the primary stage.
     *
     * @param fxmlPath Path to the FXML file (resource path)
     * @param title    Window title to set
     */
    private void loadScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            // Attempt to add stylesheet if it exists
            try {
                String stylesheet = getClass().getResource("/css/styles.css").toExternalForm();
                if (stylesheet != null) {
                    scene.getStylesheets().add(stylesheet);
                }
            } catch (Exception e) {
                logger.warn("Stylesheet not found or could not be loaded: /css/styles.css");
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();

        } catch (IOException e) {
            logger.error("Failed to load scene: {}", fxmlPath, e);
            // Show an error alert or fallback message
            try {
                AlertUtils.showError("Navigation Error", "Failed to load the requested page.");
            } catch (Exception alertEx) {
                System.err.println("Navigation Error: Failed to load " + fxmlPath);
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
        showWelcomeScene();
    }
}
