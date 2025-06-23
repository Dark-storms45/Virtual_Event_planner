package com.eventplanner.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    
    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DISPLAY_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
    
    /**
     * Format a LocalDate for display
     */
    public static String formatForDisplay(LocalDate date) {
        if (date == null) return "";
        return date.format(DISPLAY_DATE_FORMAT);
    }
    
    /**
     * Format a LocalDateTime for display
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(DISPLAY_DATETIME_FORMAT);
    }
    
    /**
     * Parse a date string using the default format
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateString.trim(), DEFAULT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }
    
    /**
     * Parse a datetime string using the default format
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeString.trim(), DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid datetime format: " + dateTimeString);
        }
    }
    
    /**
     * Check if a date is in the past
     */
    public static boolean isPast(LocalDate date) {
        if (date == null) return false;
        return date.isBefore(LocalDate.now());
    }
    
    /**
     * Check if a date is in the future
     */
    public static boolean isFuture(LocalDate date) {
        if (date == null) return false;
        return date.isAfter(LocalDate.now());
    }
    
    /**
     * Check if a date is today
     */
    public static boolean isToday(LocalDate date) {
        if (date == null) return false;
        return date.isEqual(LocalDate.now());
    }
    
    /**
     * Get the number of days between two dates
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) return 0;
        return ChronoUnit.DAYS.between(start, end);
    }
    
    /**
     * Get the number of days from today to the given date
     */
    public static long daysFromToday(LocalDate date) {
        if (date == null) return 0;
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }
    
    /**
     * Check if a date is within the next N days
     */
    public static boolean isWithinDays(LocalDate date, int days) {
        if (date == null) return false;
        LocalDate futureDate = LocalDate.now().plusDays(days);
        return !date.isBefore(LocalDate.now()) && !date.isAfter(futureDate);
    }
    
    /**
     * Get a user-friendly relative date string
     */
    public static String getRelativeDateString(LocalDate date) {
        if (date == null) return "Unknown";
        
        long daysFromToday = daysFromToday(date);
        
        if (daysFromToday == 0) {
            return "Today";
        } else if (daysFromToday == 1) {
            return "Tomorrow";
        } else if (daysFromToday == -1) {
            return "Yesterday";
        } else if (daysFromToday > 1 && daysFromToday <= 7) {
            return "In " + daysFromToday + " days";
        } else if (daysFromToday < -1 && daysFromToday >= -7) {
            return Math.abs(daysFromToday) + " days ago";
        } else {
            return formatForDisplay(date);
        }
    }
    
    /**
     * Validate if a string is a valid date
     */
    public static boolean isValidDate(String dateString) {
        try {
            parseDate(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the current date as a formatted string
     */
    public static String getCurrentDateString() {
        return LocalDate.now().format(DEFAULT_DATE_FORMAT);
    }
    
    /**
     * Get the current datetime as a formatted string
     */
    public static String getCurrentDateTimeString() {
        return LocalDateTime.now().format(DATETIME_FORMAT);
    }
}