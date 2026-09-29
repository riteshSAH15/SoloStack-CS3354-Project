package planner.service;

import planner.model.OneTimeTask;
import planner.model.Priority;
import planner.model.RecurrenceFrequency;
import planner.model.RecurringTask;
import planner.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provides the core application logic for managing tasks.
 * This class contains no console input code so it can be reused by a GUI.
 */
public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Adds a one-time task.
     * @param title title
     * @param description description
     * @param priority priority
     * @param dueDate due date
     * @return created task
     */
    public Task addOneTimeTask(
            String title,
            String description,
            Priority priority,
            LocalDate dueDate) {
        Task task = new OneTimeTask(title, description, priority, dueDate);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a recurring task.
     * @param title title
     * @param description description
     * @param priority priority
     * @param dueDate first due date
     * @param frequency recurrence frequency
     * @return created task
     */
    public Task addRecurringTask(
            String title,
            String description,
            Priority priority,
            LocalDate dueDate,
            RecurrenceFrequency frequency) {
        Task task = new RecurringTask(title, description, priority, dueDate, frequency);
        tasks.add(task);
        return task;
    }

    /** @return copy of all tasks sorted by id */
    public List<Task> getAllTasks() {
        return tasks.stream()
                .sorted(Comparator.comparingInt(Task::getId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds a task by id.
     * @param id task id
     * @return matching task if present
     */
    public Optional<Task> findById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    /**
     * Gets a task or throws a user-readable error.
     * @param id task id
     * @return task
     */
    public Task requireTask(int id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No task exists with ID " + id + "."));
    }

    /**
     * Marks a task occurrence complete.
     * @param id task id
     * @return message describing the result
     */
    public String completeTask(int id) {
        Task task = requireTask(id);
        LocalDate oldDate = task.getDueDate();
        task.complete();
        if (task instanceof RecurringTask recurringTask) {
            return "Recurring task completed. Due date moved from " + oldDate
                    + " to " + recurringTask.getDueDate() + ".";
        }
        return "Task marked completed.";
    }

    /**
     * Removes a task.
     * @param id task id
     * @return removed task
     */
    public Task removeTask(int id) {
        Task task = requireTask(id);
        tasks.remove(task);
        return task;
    }

    /**
     * Edits common task fields. Empty optional values leave the field unchanged.
     * @param id task id
     * @param title optional new title
     * @param description optional new description
     * @param priority optional new priority
     * @param dueDate optional new due date
     * @return updated task
     */
    public Task editTask(
            int id,
            String title,
            String description,
            Priority priority,
            LocalDate dueDate) {
        Task task = requireTask(id);
        if (title != null) {
            task.setTitle(title);
        }
        if (description != null) {
            task.setDescription(description);
        }
        if (priority != null) {
            task.setPriority(priority);
        }
        if (dueDate != null) {
            task.setDueDate(dueDate);
        }
        return task;
    }

    /**
     * Changes recurrence frequency for a recurring task only.
     * @param id task id
     * @param frequency new frequency
     */
    public void changeFrequency(int id, RecurrenceFrequency frequency) {
        Task task = requireTask(id);
        if (!(task instanceof RecurringTask recurringTask)) {
            throw new IllegalArgumentException("Only recurring tasks have a recurrence frequency.");
        }
        recurringTask.setFrequency(frequency);
    }

    /**
     * Filters tasks by priority.
     * @param priority priority to match
     * @return matching tasks
     */
    public List<Task> filterByPriority(Priority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }

    /**
     * Returns pending tasks due before the supplied date.
     * @param today comparison date
     * @return overdue tasks
     */
    public List<Task> getOverdueTasks(LocalDate today) {
        return tasks.stream()
                .filter(task -> task.isOverdue(today))
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }

    /**
     * Returns tasks due from today through a future number of days.
     * @param today first date in range
     * @param days number of future days to include
     * @return upcoming tasks
     */
    public List<Task> getUpcomingTasks(LocalDate today, int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Number of days cannot be negative.");
        }
        LocalDate end = today.plusDays(days);
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .filter(task -> !task.getDueDate().isBefore(today))
                .filter(task -> !task.getDueDate().isAfter(end))
                .sorted(Comparator.comparing(Task::getDueDate)
                        .thenComparing(Task::getPriority, Comparator.reverseOrder()))
                .toList();
    }

    /** @return total number of tasks */
    public int getTotalCount() {
        return tasks.size();
    }

    /** @return number of permanently completed one-time tasks */
    public long getCompletedCount() {
        return tasks.stream().filter(Task::isCompleted).count();
    }

    /** @return number of recurring tasks */
    public long getRecurringCount() {
        return tasks.stream().filter(task -> task instanceof RecurringTask).count();
    }

    /**
     * Returns number of overdue tasks.
     * @param today comparison date
     * @return overdue count
     */
    public long getOverdueCount(LocalDate today) {
        return tasks.stream().filter(task -> task.isOverdue(today)).count();
    }

    /** Populates sample data for demonstration and testing. */
    public void addSampleData() {
        LocalDate today = LocalDate.now();
        addOneTimeTask(
                "Finish OOP reading",
                "Review inheritance and polymorphism notes",
                Priority.HIGH,
                today.plusDays(1));
        addOneTimeTask(
                "Submit discussion reply",
                "Post response before the deadline",
                Priority.MEDIUM,
                today.plusDays(3));
        addRecurringTask(
                "Practice Java",
                "Complete at least one coding exercise",
                Priority.HIGH,
                today,
                RecurrenceFrequency.DAILY);
        addRecurringTask(
                "Weekly course review",
                "Review notes from all classes",
                Priority.LOW,
                today.plusDays(5),
                RecurrenceFrequency.WEEKLY);
    }
}
