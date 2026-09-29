package planner.model;

import java.time.LocalDate;

/** A task that is finished permanently after one completion. */
public class OneTimeTask extends Task {
    /**
     * Creates a one-time task.
     * @param title task title
     * @param description task description
     * @param priority priority
     * @param dueDate due date
     */
    public OneTimeTask(String title, String description, Priority priority, LocalDate dueDate) {
        super(title, description, priority, dueDate);
    }

    @Override
    public String getTaskType() {
        return "One-Time";
    }

    @Override
    public String getCompletionBehavior() {
        return "Completing this task finishes it permanently.";
    }
}
