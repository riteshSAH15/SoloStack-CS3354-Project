# Phase 1 Project Report Draft

## 1. Project Summary

**Project Title:** SoloStack Task and Study Planner  
**Student:** Ritesh Sah  
**Course:** CS 3354 – Object-Oriented Programming  
**Semester:** Fall 2026

The SoloStack Task and Study Planner is a console-based Java application intended for students who want to organize assignments and study work. Users can create one-time and recurring tasks, assign priorities and deadlines, mark work complete, edit or remove tasks, and review overdue or upcoming work.

Completed Phase 1 features include task creation, recurring task scheduling, priority levels, due dates, completion handling, editing, deletion, filtering, overdue and upcoming views, statistics, sample data, and input validation.

## 2. Object-Oriented Design

The application uses an abstract `Task` superclass that stores shared task data and behavior. `OneTimeTask` and `RecurringTask` extend `Task` and implement different completion behavior. A one-time task becomes completed permanently, while a recurring task advances to its next due date.

Polymorphism is demonstrated because `TaskManager` stores different subclasses in a `List<Task>` and the console displays and completes them through `Task` references. Overridden methods such as `getTaskType()`, `getCompletionBehavior()`, and `complete()` use the correct subclass behavior at runtime.

Encapsulation is provided by private fields and validated constructors/setters. Application responsibilities are divided among the `planner.model`, `planner.service`, and `planner.console` packages. The core logic in `TaskManager` does not depend on console input, which allows reuse in the Phase 2 GUI.

See `UML.md` for the class diagram.

## 3. Demonstration and Testing

The project was compiled with Java 17 compatibility and exercised through the console interface. Main feature tests include adding tasks, listing tasks, completing both task types, editing, removing, filtering, finding overdue/upcoming tasks, and viewing statistics.

Invalid-input tests include incorrect menu choices, nonnumeric IDs, unknown task IDs, blank titles, invalid priorities, and malformed dates. Invalid operations produce messages without corrupting the existing task collection.

**Insert final console screenshots here before submission.** Use the checklist in `TESTING.md`.

## 4. AI Disclosure

I used ChatGPT to help plan the application, interpret the project requirements, design the class structure, generate and revise portions of the Java code, prepare tests, debug compilation issues, create documentation, and review the project against the grading rubric.

Representative interactions included asking ChatGPT to identify the Phase 1 requirements, choose an approved project idea that supports inheritance and polymorphism, create the Task/OneTimeTask/RecurringTask class hierarchy, separate model/service/console responsibilities, implement input validation, and prepare README, UML, testing, and report materials.

I reviewed the generated project structure and test output, and I will run the final application myself before submission. I will also be prepared to explain the main classes, object interactions, inheritance hierarchy, polymorphic behavior, validation rules, and console workflow.

I have reviewed and tested the work I am submitting. I understand the code and take responsibility for its correctness, documentation, and compliance with the project requirements.
