package com.eventplanner.config;

public class ApiConfig {
    public static final String BASE_URL = "http://localhost:8080/api/v1";
    public static final String AUTH_LOGIN = "/auth/login";
    public static final String AUTH_REGISTER = "/auth/register";
    public static final String EVENTS = "/events";
    public static final String VENDORS = "/vendors";
    public static final String BUDGETS = "/budgets";
    
    // Timeouts
    public static final int CONNECTION_TIMEOUT = 30; // seconds
    public static final int READ_TIMEOUT = 30; // seconds
    public static final int WRITE_TIMEOUT = 30; // seconds
}