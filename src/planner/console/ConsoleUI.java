package planner.console;

import planner.model.Priority;
import planner.model.RecurrenceFrequency;
import planner.model.Task;
import planner.service.TaskManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Console interface for the Task and Study Planner. */
public class ConsoleUI {
    private final TaskManager manager;
    private final Scanner scanner;
    private boolean running = true;

    /**
     * Creates the console UI.
     * @param manager application service
     * @param scanner scanner for keyboard input
     */
    public ConsoleUI(TaskManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    /** Starts the repeated console menu until the user exits. */
    public void run() {
        printWelcome();
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                handleChoice(choice);
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("ERROR: " + exception.getMessage());
            }
            System.out.println();
        }
        System.out.println("Goodbye. Your planner session has ended cleanly.");
    }

    private void printWelcome() {
        System.out.println("========================================");
        System.out.println("       SoloStack Task & Study Planner");
        System.out.println("========================================");
        System.out.println("Console prototype for CS 3354 Phase 1");
        System.out.println();
    }

    private void printMenu() {
        System.out.println("1. Add one-time task");
        System.out.println("2. Add recurring task");
        System.out.println("3. List all tasks");
        System.out.println("4. View task details");
        System.out.println("5. Complete task");
        System.out.println("6. Edit task");
        System.out.println("7. Remove task");
        System.out.println("8. Filter by priority");
        System.out.println("9. Show overdue tasks");
        System.out.println("10. Show upcoming tasks");
        System.out.println("11. Show statistics");
        System.out.println("12. Load sample data");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "1" -> addOneTimeTask();
            case "2" -> addRecurringTask();
            case "3" -> listAllTasks();
            case "4" -> showTaskDetails();
            case "5" -> completeTask();
            case "6" -> editTask();
            case "7" -> removeTask();
            case "8" -> filterByPriority();
            case "9" -> showOverdueTasks();
            case "10" -> showUpcomingTasks();
            case "11" -> showStatistics();
            case "12" -> loadSampleData();
            case "0", "q", "quit", "exit" -> running = false;
            default -> System.out.println("Invalid menu choice. Enter a number from 0 to 12.");
        }
    }

    private void addOneTimeTask() {
        System.out.println("--- Add One-Time Task ---");
        String title = readRequired("Title: ");
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Priority priority = readPriority(false);
        LocalDate dueDate = readDate("Due date (YYYY-MM-DD): ");
        Task task = manager.addOneTimeTask(title, description, priority, dueDate);
        System.out.println("Added task #" + task.getId() + ".");
    }

    private void addRecurringTask() {
        System.out.println("--- Add Recurring Task ---");
        String title = readRequired("Title: ");
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Priority priority = readPriority(false);
        LocalDate dueDate = readDate("First due date (YYYY-MM-DD): ");
        RecurrenceFrequency frequency = readFrequency(false);
        Task task = manager.addRecurringTask(
                title, description, priority, dueDate, frequency);
        System.out.println("Added recurring task #" + task.getId() + ".");
    }

    private void listAllTasks() {
        System.out.println("--- All Tasks ---");
        printTasks(manager.getAllTasks());
    }

    private void showTaskDetails() {
        int id = readTaskId();
        System.out.println("--- Task Details ---");
        System.out.println(manager.requireTask(id).toDetailedString());
    }

    private void completeTask() {
        int id = readTaskId();
        String message = manager.completeTask(id);
        System.out.println(message);
    }

    private void editTask() {
        int id = readTaskId();
        Task task = manager.requireTask(id);
        System.out.println("Press Enter to leave a value unchanged.");
        System.out.print("New title [" + task.getTitle() + "]: ");
        String titleInput = scanner.nextLine();
        String title = titleInput.isBlank() ? null : titleInput;
        System.out.print("New description [" + task.getDescription() + "]: ");
        String descriptionInput = scanner.nextLine();
        String description = descriptionInput.isBlank() ? null : descriptionInput;
        Priority priority = readPriority(true);
        System.out.print("New due date [" + task.getDueDate() + "] (YYYY-MM-DD): ");
        LocalDate dueDate = InputParser.parseOptionalDate(scanner.nextLine());
        manager.editTask(id, title, description, priority, dueDate);
        System.out.println("Task updated.");
    }

    private void removeTask() {
        int id = readTaskId();
        Task task = manager.requireTask(id);
        System.out.print("Remove '" + task.getTitle() + "'? (y/n): ");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            manager.removeTask(id);
            System.out.println("Task removed.");
        } else {
            System.out.println("Removal canceled.");
        }
    }

    private void filterByPriority() {
        Priority priority = readPriority(false);
        System.out.println("--- " + priority + " Priority Tasks ---");
        printTasks(manager.filterByPriority(priority));
    }

    private void showOverdueTasks() {
        LocalDate today = LocalDate.now();
        System.out.println("--- Overdue Tasks as of " + today + " ---");
        printTasks(manager.getOverdueTasks(today));
    }

    private void showUpcomingTasks() {
        System.out.print("Show tasks due within how many days? ");
        int days = InputParser.parsePositiveInt(scanner.nextLine(), "Days");
        LocalDate today = LocalDate.now();
        System.out.println("--- Upcoming Tasks ---");
        printTasks(manager.getUpcomingTasks(today, days));
    }

    private void showStatistics() {
        LocalDate today = LocalDate.now();
        System.out.println("--- Planner Statistics ---");
        System.out.println("Total tasks: " + manager.getTotalCount());
        System.out.println("Completed one-time tasks: " + manager.getCompletedCount());
        System.out.println("Recurring tasks: " + manager.getRecurringCount());
        System.out.println("Overdue tasks: " + manager.getOverdueCount(today));
    }

    private void loadSampleData() {
        manager.addSampleData();
        System.out.println("Sample tasks added for demonstration.");
    }

    private int readTaskId() {
        System.out.print("Task ID: ");
        return InputParser.parsePositiveInt(scanner.nextLine(), "Task ID");
    }

    private String readRequired(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("This value cannot be blank.");
        }
        return value;
    }

    private LocalDate readDate(String prompt) {
        System.out.print(prompt);
        return InputParser.parseDate(scanner.nextLine());
    }

    private Priority readPriority(boolean optional) {
        System.out.print(optional
                ? "New priority (low/medium/high, Enter to keep current): "
                : "Priority (low/medium/high): ");
        String text = scanner.nextLine();
        if (optional && text.trim().isEmpty()) {
            return null;
        }
        return Priority.fromString(text);
    }

    private RecurrenceFrequency readFrequency(boolean optional) {
        System.out.print(optional
                ? "New frequency (daily/weekly/monthly, Enter to keep current): "
                : "Frequency (daily/weekly/monthly): ");
        String text = scanner.nextLine();
        if (optional && text.trim().isEmpty()) {
            return null;
        }
        return RecurrenceFrequency.fromString(text);
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks.");
            return;
        }
        System.out.println("ID   | Type      | Priority  | Status     | Due Date   | Title");
        System.out.println("--------------------------------------------------------------------------");
        for (Task task : tasks) {
            System.out.println(task.toDisplayString());
        }
    }
}
