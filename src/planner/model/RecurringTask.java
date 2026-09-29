package planner.model;

import java.time.LocalDate;
import java.util.Objects;

/** A recurring task that advances to a new due date after each completion. */
public class RecurringTask extends Task {
    private RecurrenceFrequency frequency;
    private int completionCount;

    /**
     * Creates a recurring task.
     * @param title task title
     * @param description task description
     * @param priority priority
     * @param dueDate first due date
     * @param frequency recurrence interval
     */
    public RecurringTask(
            String title,
            String description,
            Priority priority,
            LocalDate dueDate,
            RecurrenceFrequency frequency) {
        super(title, description, priority, dueDate);
        setFrequency(frequency);
    }

    /** @return recurrence frequency */
    public RecurrenceFrequency getFrequency() {
        return frequency;
    }

    /**
     * Changes recurrence frequency.
     * @param frequency non-null recurrence frequency
     */
    public void setFrequency(RecurrenceFrequency frequency) {
        this.frequency = Objects.requireNonNull(frequency, "Frequency cannot be null.");
    }

    /** @return number of completed occurrences */
    public int getCompletionCount() {
        return completionCount;
    }

    /** @return next due date based on the current due date */
    public LocalDate previewNextDueDate() {
        return frequency.nextDate(getDueDate());
    }

    /**
     * Completes the current occurrence and advances the due date.
     * The recurring task remains pending because another occurrence is created.
     */
    @Override
    public void complete() {
        completionCount++;
        setDueDate(previewNextDueDate());
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public String getTaskType() {
        return "Recurring";
    }

    @Override
    public String getCompletionBehavior() {
        return "Each completion advances the due date by one "
                + frequency.name().toLowerCase() + " interval.";
    }

    @Override
    public String toDetailedString() {
        return super.toDetailedString()
                + System.lineSeparator()
                + "Frequency: " + frequency
                + System.lineSeparator()
                + "Completed occurrences: " + completionCount;
    }
}
