package planner.console;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** Utility methods that validate and convert console text. */
public final class InputParser {
    private InputParser() {
    }

    /**
     * Parses a positive integer.
     * @param text user input
     * @param field field name used in the error message
     * @return parsed positive integer
     */
    public static int parsePositiveInt(String text, String field) {
        try {
            int value = Integer.parseInt(text.trim());
            if (value <= 0) {
                throw new IllegalArgumentException(field + " must be greater than zero.");
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(field + " must be a whole number.");
        }
    }

    /**
     * Parses an ISO date such as 2026-10-05.
     * @param text user input
     * @return parsed date
     */
    public static LocalDate parseDate(String text) {
        try {
            return LocalDate.parse(text.trim());
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date must use YYYY-MM-DD format.");
        }
    }

    /**
     * Parses an optional date. Blank input means no change.
     * @param text user input
     * @return parsed date or null
     */
    public static LocalDate parseOptionalDate(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        return parseDate(text);
    }
}
