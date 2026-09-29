# Phase 1 Project Plan

## Project
Task and Study Planner

## Goal
Build a console-based Java application that lets a student create and manage study tasks, priorities, deadlines, completion status, and recurring work. Core logic will remain separate from console input/output so it can be reused in Phase 2 with a GUI.

## Planned OOP Design
- `Task` abstract superclass
- `OneTimeTask` subclass
- `RecurringTask` subclass
- Polymorphic behavior for completion and next-due-date handling
- Encapsulated fields with validation
- Separate model, service, and console packages

## Planned Packages
- `planner.model` — task classes, enums, domain data
- `planner.service` — task management and application logic
- `planner.console` — console UI, input parsing, and `main`

## Planned Phase 1 Features
- Add one-time tasks
- Add recurring tasks
- List all tasks
- Filter tasks by status, priority, or due date
- Mark tasks complete
- Calculate the next due date for recurring tasks
- Edit task information
- Remove tasks
- Show overdue and upcoming tasks
- Display task statistics
- Validate bad input and invalid operations without corrupting program state
- Repeated console operations with a clean exit

## Submission Checklist
- [ ] Working console application
- [ ] Multiple classes with clear responsibilities
- [ ] Meaningful superclass and multiple subclasses
- [ ] Demonstrated polymorphism through superclass references
- [ ] Encapsulation and validation
- [ ] At least two named packages
- [ ] Core logic separated from console UI
- [ ] At least 500 nonblank, non-comment Java application lines
- [ ] Javadoc for classes, methods, and attributes
- [ ] Generated Javadoc HTML with `index.html`
- [ ] Multiple meaningful GitHub commits
- [ ] Complete README with Java version, entry point, setup/run steps, features, limitations, and sample use
- [ ] UML class diagram
- [ ] Console screenshots for successful, invalid, and boundary-case tests
- [ ] Project report in PDF or Word
- [ ] Required AI disclosure and responsibility statement

## AI Disclosure Plan
The final report will state that ChatGPT was used to help plan, generate, review, test, and document portions of the project. The submitted disclosure will also explain how the code was reviewed and tested and will include the required responsibility statement from the assignment instructions.
