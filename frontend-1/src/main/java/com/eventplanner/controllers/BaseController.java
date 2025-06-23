package com.eventplanner.controllers;

import com.eventplanner.utils.SceneManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {
    protected SceneManager sceneManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = SceneManager.getInstance();
        initializeController();
    }

    protected abstract void initializeController();

    protected void showError(String title, String message) {
        // This will be implemented with proper JavaFX Alert
        System.err.println("ERROR - " + title + ": " + message);
    }

    protected void showInfo(String title, String message) {
        // This will be implemented with proper JavaFX Alert
        System.out.println("INFO - " + title + ": " + message);
    }
}