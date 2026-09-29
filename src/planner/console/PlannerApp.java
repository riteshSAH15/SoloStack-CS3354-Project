package planner.console;

import planner.service.TaskManager;

import java.util.Scanner;

/** Entry-point class for the SoloStack Task and Study Planner. */
public final class PlannerApp {
    private PlannerApp() {
    }

    /**
     * Starts the application.
     * @param args command-line arguments, currently unused
     */
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleUI ui = new ConsoleUI(manager, scanner);
            ui.run();
        }
    }
}
