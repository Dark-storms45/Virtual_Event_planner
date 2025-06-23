package com.eventplanner.services;

import com.eventplanner.config.ApiConfig;
import com.eventplanner.models.Vendor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class VendorService {
    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public VendorService() {
        this.apiClient = ApiClient.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    public List<Vendor> getAllVendors() throws IOException {
        logger.info("Fetching all vendors");
        
        try {
            String response = apiClient.get(ApiConfig.VENDORS, String.class);
            return objectMapper.readValue(response, new TypeReference<List<Vendor>>() {});
        } catch (IOException e) {
            logger.error("Failed to fetch vendors", e);
            throw new IOException("Failed to fetch vendors: " + e.getMessage());
        }
    }

    public Vendor createVendor(Vendor vendor) throws IOException {
        logger.info("Creating new vendor: {}", vendor.getName());
        
        try {
            return apiClient.post(ApiConfig.VENDORS, vendor, Vendor.class);
        } catch (IOException e) {
            logger.error("Failed to create vendor: {}", vendor.getName(), e);
            throw new IOException("Failed to create vendor: " + e.getMessage());
        }
    }

    public Vendor updateVendor(Vendor vendor) throws IOException {
        logger.info("Updating vendor: {}", vendor.getName());
        
        try {
            return apiClient.put(ApiConfig.VENDORS + "/" + vendor.getId(), vendor, Vendor.class);
        } catch (IOException e) {
            logger.error("Failed to update vendor: {}", vendor.getName(), e);
            throw new IOException("Failed to update vendor: " + e.getMessage());
        }
    }

    public boolean deleteVendor(Long id) throws IOException {
        logger.info("Deleting vendor with id: {}", id);
        
        try {
            return apiClient.delete(ApiConfig.VENDORS + "/" + id);
        } catch (IOException e) {
            logger.error("Failed to delete vendor with id: {}", id, e);
            throw new IOException("Failed to delete vendor: " + e.getMessage());
        }
    }
}