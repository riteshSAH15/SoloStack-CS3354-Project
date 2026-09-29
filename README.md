# SoloStack Task and Study Planner

## CS 3354 – Object-Oriented Programming

**Semester:** Fall 2026  
**Team Name:** SoloStack  
**Team Member:** Ritesh Sah  
**Project:** Task and Study Planner

## Purpose

This Phase 1 project is a console-based Java application for students who want to organize study tasks and deadlines. It supports one-time and recurring tasks and demonstrates object-oriented programming concepts that can be reused when a GUI is added in Phase 2.

## Java Requirement

Use **Java 17 or newer**.

## Entry Point

`planner.console.PlannerApp`

## Completed Features

- Add one-time tasks
- Add recurring tasks
- Assign low, medium, or high priority
- Store descriptions and due dates
- List all tasks
- View detailed task information
- Mark one-time tasks complete
- Complete recurring occurrences and automatically advance their due dates
- Edit tasks
- Remove tasks with confirmation
- Filter by priority
- Show overdue tasks
- Show upcoming tasks
- Display planner statistics
- Validate bad menu choices, IDs, priorities, dates, and invalid operations
- Load sample data for demonstration

## Object-Oriented Design

The project uses an abstract `Task` superclass with `OneTimeTask` and `RecurringTask` subclasses. Subclasses override task behavior, and the application stores and processes them through `Task` references to demonstrate polymorphism. Fields are encapsulated and validated through constructors and methods.

Packages are separated by responsibility:

- `planner.model` – domain classes and enums
- `planner.service` – core application logic
- `planner.console` – console input/output and the main method

## Compile and Run

From the repository root on Windows PowerShell:

```powershell
mkdir out
javac -d out (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
java -cp out planner.console.PlannerApp
```

Or from a Bash-compatible terminal:

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out planner.console.PlannerApp
```

## Quick Demonstration

Run the program and choose option **12** to load sample data. Then use option **3** to list tasks, option **5** to complete a task, option **9** to view overdue tasks, and option **11** to view statistics.

## Documentation

- `PROJECT_PLAN.md` – Phase 1 plan and checklist
- `UML.md` – UML class diagram
- `TESTING.md` – manual test plan and expected results
- `REPORT_DRAFT.md` – report content to be transferred to the final Word/PDF report
- `docs/javadoc/index.html` – generated Javadoc entry point

## Known Limitations

- Data is stored in memory only and is not saved after the program exits.
- Phase 1 uses a console interface only.
- Recurring tasks support daily, weekly, and monthly intervals.
- Editing a recurring task's recurrence frequency is supported in the service layer but is not currently exposed as a separate console menu option.

## Repository

https://github.com/riteshSAH15/SoloStack-CS3354-Project
