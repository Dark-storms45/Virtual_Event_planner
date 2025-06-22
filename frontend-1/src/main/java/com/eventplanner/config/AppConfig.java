package com.eventplanner.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private static AppConfig instance;
    private Properties properties;
    
    // Default configuration values
    private static final String DEFAULT_API_BASE_URL = "http://localhost:8080/api";
    private static final String DEFAULT_APP_NAME = "Event Planner";
    private static final String DEFAULT_APP_VERSION = "1.0.0";
    private static final String DEFAULT_CONNECTION_TIMEOUT = "30000";
    private static final String DEFAULT_READ_TIMEOUT = "30000";
    
    private AppConfig() {
        loadProperties();
    }
    
    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
    
    private void loadProperties() {
        properties = new Properties();
        
        // Load from application.properties file
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("Application properties loaded successfully");
            } else {
                logger.warn("application.properties file not found, using default values");
                loadDefaultProperties();
            }
        } catch (IOException e) {
            logger.error("Error loading application properties", e);
            loadDefaultProperties();
        }
    }
    
    private void loadDefaultProperties() {
        properties.setProperty("api.base.url", DEFAULT_API_BASE_URL);
        properties.setProperty("app.name", DEFAULT_APP_NAME);
        properties.setProperty("app.version", DEFAULT_APP_VERSION);
        properties.setProperty("connection.timeout", DEFAULT_CONNECTION_TIMEOUT);
        properties.setProperty("read.timeout", DEFAULT_READ_TIMEOUT);
        properties.setProperty("ui.theme", "light");
        properties.setProperty("auto.save", "true");
        properties.setProperty("remember.login", "false");
    }
    
    // API Configuration
    public String getApiBaseUrl() {
        return properties.getProperty("api.base.url", DEFAULT_API_BASE_URL);
    }
    
    public int getConnectionTimeout() {
        return Integer.parseInt(properties.getProperty("connection.timeout", DEFAULT_CONNECTION_TIMEOUT));
    }
    
    public int getReadTimeout() {
        return Integer.parseInt(properties.getProperty("read.timeout", DEFAULT_READ_TIMEOUT));
    }
    
    // Application Configuration
    public String getAppName() {
        return properties.getProperty("app.name", DEFAULT_APP_NAME);
    }
    
    public String getAppVersion() {
        return properties.getProperty("app.version", DEFAULT_APP_VERSION);
    }
    
    public String getUiTheme() {
        return properties.getProperty("ui.theme", "light");
    }
    
    public boolean isAutoSaveEnabled() {
        return Boolean.parseBoolean(properties.getProperty("auto.save", "true"));
    }
    
    public boolean isRememberLoginEnabled() {
        return Boolean.parseBoolean(properties.getProperty("remember.login", "false"));
    }
    
    // Window Configuration
    public double getWindowWidth() {
        return Double.parseDouble(properties.getProperty("window.width", "1200"));
    }
    
    public double getWindowHeight() {
        return Double.parseDouble(properties.getProperty("window.height", "800"));
    }
    
    public boolean isWindowMaximized() {
        return Boolean.parseBoolean(properties.getProperty("window.maximized", "false"));
    }
    
    // Database Configuration (if needed for local storage)
    public String getDatabasePath() {
        return properties.getProperty("database.path", "data/eventplanner.db");
    }
    
    public boolean isDatabaseAutoCreateEnabled() {
        return Boolean.parseBoolean(properties.getProperty("database.auto.create", "true"));
    }
    
    // Logging Configuration
    public String getLogLevel() {
        return properties.getProperty("log.level", "INFO");
    }
    
    public String getLogFilePath() {
        return properties.getProperty("log.file.path", "logs/eventplanner.log");
    }
    
    // Feature Flags
    public boolean isVendorManagementEnabled() {
        return Boolean.parseBoolean(properties.getProperty("feature.vendor.management", "true"));
    }
    
    public boolean isBudgetTrackingEnabled() {
        return Boolean.parseBoolean(properties.getProperty("feature.budget.tracking", "true"));
    }
    
    public boolean isNotificationsEnabled() {
        return Boolean.parseBoolean(properties.getProperty("feature.notifications", "true"));
    }
    
    // Utility methods
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
    
    public void reloadProperties() {
        loadProperties();
        logger.info("Application properties reloaded");
    }
}