package planner.model;

import java.time.LocalDate;

/** Supported recurrence intervals for recurring tasks. */
public enum RecurrenceFrequency {
    DAILY,
    WEEKLY,
    MONTHLY;

    /**
     * Calculates the next date using this recurrence interval.
     * @param current current due date
     * @return next due date
     */
    public LocalDate nextDate(LocalDate current) {
        return switch (this) {
            case DAILY -> current.plusDays(1);
            case WEEKLY -> current.plusWeeks(1);
            case MONTHLY -> current.plusMonths(1);
        };
    }

    /**
     * Parses a recurrence frequency from user text.
     * @param text text to parse
     * @return matching recurrence frequency
     */
    public static RecurrenceFrequency fromString(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Frequency cannot be null.");
        }
        return switch (text.trim().toLowerCase()) {
            case "1", "daily", "day" -> DAILY;
            case "2", "weekly", "week" -> WEEKLY;
            case "3", "monthly", "month" -> MONTHLY;
            default -> throw new IllegalArgumentException(
                    "Frequency must be daily, weekly, or monthly.");
        };
    }
}
