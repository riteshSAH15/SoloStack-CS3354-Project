package planner.model;

/** Priority levels for planner tasks. */
public enum Priority {
    /** Low priority. */
    LOW,
    /** Medium priority. */
    MEDIUM,
    /** High priority. */
    HIGH;

    /**
     * Parses a priority from user text.
     * @param text text to parse
     * @return matching priority
     * @throws IllegalArgumentException when no priority matches
     */
    public static Priority fromString(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        return switch (text.trim().toLowerCase()) {
            case "1", "low" -> LOW;
            case "2", "medium", "med" -> MEDIUM;
            case "3", "high" -> HIGH;
            default -> throw new IllegalArgumentException("Priority must be low, medium, or high.");
        };
    }
}
