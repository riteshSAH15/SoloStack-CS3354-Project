# UML Class Diagram

```mermaid
classDiagram
direction LR

class Task {
  <<abstract>>
  -int id
  -String title
  -String description
  -Priority priority
  -LocalDate dueDate
  -TaskStatus status
  +getId() int
  +getTitle() String
  +setTitle(String)
  +setDescription(String)
  +setPriority(Priority)
  +setDueDate(LocalDate)
  +isCompleted() boolean
  +isOverdue(LocalDate) boolean
  +complete()
  +getTaskType()* String
  +getCompletionBehavior()* String
}

class OneTimeTask {
  +complete()
  +getTaskType() String
  +getCompletionBehavior() String
}

class RecurringTask {
  -RecurrenceFrequency frequency
  -int completionCount
  +complete()
  +previewNextDueDate() LocalDate
  +getTaskType() String
  +getCompletionBehavior() String
}

class TaskManager {
  -List~Task~ tasks
  +addOneTimeTask(...) Task
  +addRecurringTask(...) Task
  +getAllTasks() List~Task~
  +requireTask(int) Task
  +completeTask(int) String
  +editTask(...) Task
  +removeTask(int) Task
  +filterByPriority(Priority) List~Task~
  +getOverdueTasks(LocalDate) List~Task~
  +getUpcomingTasks(LocalDate,int) List~Task~
}

class ConsoleUI {
  -TaskManager manager
  -Scanner scanner
  +run()
}

class PlannerApp {
  +main(String[])
}

class InputParser {
  +parsePositiveInt(String,String) int
  +parseDate(String) LocalDate
  +parseOptionalDate(String) LocalDate
}

class Priority {
  <<enumeration>>
  LOW
  MEDIUM
  HIGH
}

class TaskStatus {
  <<enumeration>>
  PENDING
  COMPLETED
}

class RecurrenceFrequency {
  <<enumeration>>
  DAILY
  WEEKLY
  MONTHLY
  +nextDate(LocalDate) LocalDate
}

Task <|-- OneTimeTask
Task <|-- RecurringTask
Task --> Priority
Task --> TaskStatus
RecurringTask --> RecurrenceFrequency
TaskManager "1" o-- "*" Task
ConsoleUI --> TaskManager
ConsoleUI --> InputParser
PlannerApp --> ConsoleUI
PlannerApp --> TaskManager
```

## Package Organization

- **planner.model**: Task, OneTimeTask, RecurringTask, Priority, TaskStatus, RecurrenceFrequency
- **planner.service**: TaskManager
- **planner.console**: PlannerApp, ConsoleUI, InputParser
