package com.eventplanner.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Validator {
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    
    // Phone number pattern (supports various formats)
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^[\\+]?[1-9]?[0-9]{7,15}$"
    );
    
    // Name pattern (letters, spaces, hyphens, apostrophes)
    private static final Pattern NAME_PATTERN = Pattern.compile(
        "^[a-zA-Z\\s\\-\\']{2,50}$"
    );
    
    // Password pattern (at least 8 characters, contains letter and number)
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$"
    );
    
    /**
     * Validate email address
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    /**
     * Validate phone number
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return true; // Phone is optional in many cases
        }
        // Remove all non-digit characters except +
        String cleanPhone = phone.replaceAll("[^\\d\\+]", "");
        return PHONE_PATTERN.matcher(cleanPhone).matches();
    }
    
    /**
     * Validate name (first name, last name, etc.)
     */
    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name.trim()).matches();
    }
    
    /**
     * Validate password strength
     */
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
    
    /**
     * Check if string is not null and not empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * Check if string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Validate numeric string
     */
    public static boolean isValidNumber(String number) {
        if (isEmpty(number)) return false;
        try {
            Double.parseDouble(number.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate integer string
     */
    public static boolean isValidInteger(String number) {
        if (isEmpty(number)) return false;
        try {
            Integer.parseInt(number.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate positive integer
     */
    public static boolean isValidPositiveInteger(String number) {
        if (!isValidInteger(number)) return false;
        try {
            int value = Integer.parseInt(number.trim());
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate decimal (for currency/budget amounts)
     */
    public static boolean isValidDecimal(String decimal) {
        if (isEmpty(decimal)) return false;
        try {
            new BigDecimal(decimal.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate positive decimal
     */
    public static boolean isValidPositiveDecimal(String decimal) {
        if (!isValidDecimal(decimal)) return false;
        try {
            BigDecimal value = new BigDecimal(decimal.trim());
            return value.compareTo(BigDecimal.ZERO) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate URL format
     */
    public static boolean isValidUrl(String url) {
        if (isEmpty(url)) return true; // URL is optional
        try {
            new java.net.URL(url.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validate string length
     */
    public static boolean isValidLength(String str, int minLength, int maxLength) {
        if (str == null) return false;
        int length = str.trim().length();
        return length >= minLength && length <= maxLength;
    }
    
    /**
     * Get password strength description
     */
    public static String getPasswordStrengthMessage(String password) {
        if (isEmpty(password)) {
            return "Password is required";
        }
        
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }
        
        if (!password.matches(".*[A-Za-z].*")) {
            return "Password must contain at least one letter";
        }
        
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one number";
        }
        
        return "Password is valid";
    }
    
    /**
     * Sanitize input string (remove potentially harmful characters)
     */
    public static String sanitizeInput(String input) {
        if (input == null) return null;
        
        // Remove potential script tags and other harmful content
        return input.replaceAll("<script.*?</script>", "")
                   .replaceAll("<.*?>", "")
                   .trim();
    }
}