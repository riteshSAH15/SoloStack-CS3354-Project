package planner.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Abstract base class for all planner tasks.
 * Shared task data and validation live here so every task remains valid.
 */
public abstract class Task {
    private static int nextId = 1;

    private final int id;
    private String title;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private TaskStatus status;

    /**
     * Creates a new task.
     * @param title short task title
     * @param description optional task details
     * @param priority priority level
     * @param dueDate due date
     */
    protected Task(String title, String description, Priority priority, LocalDate dueDate) {
        this.id = nextId++;
        setTitle(title);
        setDescription(description);
        setPriority(priority);
        setDueDate(dueDate);
        this.status = TaskStatus.PENDING;
    }

    /** @return unique task id */
    public int getId() {
        return id;
    }

    /** @return task title */
    public String getTitle() {
        return title;
    }

    /**
     * Changes the title.
     * @param title new nonblank title
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        this.title = title.trim();
    }

    /** @return task description */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description.
     * @param description new description, or null for empty
     */
    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    /** @return task priority */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Changes priority.
     * @param priority non-null priority
     */
    public void setPriority(Priority priority) {
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null.");
    }

    /** @return task due date */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Changes due date.
     * @param dueDate non-null due date
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = Objects.requireNonNull(dueDate, "Due date cannot be null.");
    }

    /** @return current task status */
    public TaskStatus getStatus() {
        return status;
    }

    /** @return true when the task is completed */
    public boolean isCompleted() {
        return status == TaskStatus.COMPLETED;
    }

    /**
     * Returns whether this task is overdue on a given date.
     * @param today comparison date
     * @return true when pending and due before today
     */
    public boolean isOverdue(LocalDate today) {
        Objects.requireNonNull(today, "Comparison date cannot be null.");
        return !isCompleted() && dueDate.isBefore(today);
    }

    /** Marks this task as completed. Subclasses may extend this behavior. */
    public void complete() {
        if (isCompleted()) {
            throw new IllegalStateException("Task is already completed.");
        }
        status = TaskStatus.COMPLETED;
    }

    /** Returns the task type shown in the console. */
    public abstract String getTaskType();

    /**
     * Returns a human-readable description of what happens after completion.
     * @return completion behavior text
     */
    public abstract String getCompletionBehavior();

    /**
     * Builds one line of task information for lists.
     * @return formatted task summary
     */
    public String toDisplayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return String.format(
                "#%d | %-9s | %-9s | %-10s | %s | %s",
                id,
                getTaskType(),
                priority,
                status,
                dueDate.format(formatter),
                title);
    }

    /** @return complete details for a task */
    public String toDetailedString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(id).append(System.lineSeparator());
        builder.append("Type: ").append(getTaskType()).append(System.lineSeparator());
        builder.append("Title: ").append(title).append(System.lineSeparator());
        builder.append("Description: ")
                .append(description.isBlank() ? "(none)" : description)
                .append(System.lineSeparator());
        builder.append("Priority: ").append(priority).append(System.lineSeparator());
        builder.append("Due date: ").append(dueDate).append(System.lineSeparator());
        builder.append("Status: ").append(status).append(System.lineSeparator());
        builder.append("Completion behavior: ").append(getCompletionBehavior());
        return builder.toString();
    }
}
