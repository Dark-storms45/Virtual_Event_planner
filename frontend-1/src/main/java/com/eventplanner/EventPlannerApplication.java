package com.eventplanner;

import com.eventplanner.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventPlannerApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(EventPlannerApplication.class);
    
    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Starting EventPlanner Application");
            
            SceneManager.getInstance().setPrimaryStage(primaryStage);
            SceneManager.getInstance().showWelcomeScene();
            
            primaryStage.setTitle("EventPro - Professional Event Planning");
            primaryStage.setMinWidth(1200);
            primaryStage.setMinHeight(800);
            primaryStage.show();
            
        } catch (Exception e) {
            logger.error("Error starting application", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}