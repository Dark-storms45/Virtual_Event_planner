package com.eventplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget {
    @JsonProperty("id")
    private Long id;
    
    @NotNull(message = "Event ID is required")
    @JsonProperty("event_id")
    private Long eventId;
    
    @NotBlank(message = "Category is required")
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("description")
    private String description;
    
    @Positive(message = "Allocated amount must be positive")
    @JsonProperty("allocated_amount")
    private BigDecimal allocatedAmount;
    
    @JsonProperty("spent_amount")
    private BigDecimal spentAmount = BigDecimal.ZERO;
    
    @JsonProperty("vendor_id")
    private Long vendorId;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Budget() {}
    
    public Budget(Long eventId, String category, BigDecimal allocatedAmount) {
        this.eventId = eventId;
        this.category = category;
        this.allocatedAmount = allocatedAmount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getAllocatedAmount() { return allocatedAmount; }
    public void setAllocatedAmount(BigDecimal allocatedAmount) { this.allocatedAmount = allocatedAmount; }
    
    public BigDecimal getSpentAmount() { return spentAmount; }
    public void setSpentAmount(BigDecimal spentAmount) { this.spentAmount = spentAmount; }
    
    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public BigDecimal getRemainingAmount() {
        return allocatedAmount.subtract(spentAmount);
    }
}