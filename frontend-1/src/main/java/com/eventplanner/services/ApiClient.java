package com.eventplanner.services;

import com.eventplanner.config.ApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private static ApiClient instance;
    
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private String authToken;

    private ApiClient() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public void setAuthToken(String token) {
        this.authToken = token;
    }

    public <T> T get(String endpoint, Class<T> responseClass) throws IOException {
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URL + endpoint)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request failed: " + response.code());
            }
            
            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, responseClass);
        }
    }

    public <T> T post(String endpoint, Object requestBody, Class<T> responseClass) throws IOException {
        String json = objectMapper.writeValueAsString(requestBody);
        
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URL + endpoint)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request failed: " + response.code());
            }
            
            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, responseClass);
        }
    }

    public <T> T put(String endpoint, Object requestBody, Class<T> responseClass) throws IOException {
        String json = objectMapper.writeValueAsString(requestBody);
        
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URL + endpoint)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", "application/json")
                .put(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request failed: " + response.code());
            }
            
            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, responseClass);
        }
    }

    public boolean delete(String endpoint) throws IOException {
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URL + endpoint)
                .addHeader("Authorization", "Bearer " + authToken)
                .delete()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }
}