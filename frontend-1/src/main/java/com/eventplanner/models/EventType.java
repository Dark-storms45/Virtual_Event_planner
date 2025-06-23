package com.eventplanner.models;

public enum EventType {
    WEDDING("Wedding"),
    CORPORATE("Corporate Event"),
    BIRTHDAY("Birthday Party"),
    CONFERENCE("Conference"),
    GRADUATION("Graduation"),
    ANNIVERSARY("Anniversary"),
    BABY_SHOWER("Baby Shower"),
    OTHER("Other");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}