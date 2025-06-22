package com.eventplanner.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertUtils {
    
    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Set icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        try {
            stage.getIcons().add(new Image(AlertUtils.class.getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        alert.showAndWait();
    }
    
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Set icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        try {
            stage.getIcons().add(new Image(AlertUtils.class.getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        alert.showAndWait();
    }
    
    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Set icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        try {
            stage.getIcons().add(new Image(AlertUtils.class.getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        alert.showAndWait();
    }
    
    public static boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Set icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        try {
            stage.getIcons().add(new Image(AlertUtils.class.getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    
    public static void showSuccess(String message) {
        showInfo("Success", message);
    }
    
    public static void showErrorWithDetails(String title, String message, String details) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(details);
        
        // Set icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        try {
            stage.getIcons().add(new Image(AlertUtils.class.getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        alert.getDialogPane().setExpandableContent(null);
        alert.showAndWait();
    }
}