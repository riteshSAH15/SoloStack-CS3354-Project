# Phase 1 Testing Plan

The application was compiled successfully with Java 17 compatibility.

## Main Feature Tests

| Test | Steps | Expected Result |
|---|---|---|
| Load sample data | Choose 12 | Four sample tasks are added |
| List tasks | Choose 3 | All tasks display with ID, type, priority, status, due date, and title |
| Complete one-time task | Choose 5 and enter a one-time task ID | Status changes to COMPLETED |
| Complete recurring task | Choose 5 and enter a recurring task ID | Due date advances by its recurrence interval and task remains active |
| View details | Choose 4 and enter a valid ID | Full task details display |
| Edit task | Choose 6 and change selected fields | Only entered fields change |
| Remove task | Choose 7, valid ID, then y | Task is removed |
| Priority filter | Choose 8 and enter high | Only high-priority tasks display |
| Overdue view | Choose 9 | Pending tasks with dates before today display |
| Upcoming view | Choose 10 and enter a positive day count | Tasks due within the requested date range display |
| Statistics | Choose 11 | Counts for total, completed, recurring, and overdue tasks display |
| Clean exit | Choose 0 | Program exits without an exception |

## Invalid Input and Boundary Tests

| Input/Action | Expected Result |
|---|---|
| Menu choice 99 | Clear invalid-menu message; program continues |
| Task ID abc | Clear whole-number validation error |
| Task ID 9999 | Clear no-task-found message |
| Blank title | Clear blank-value error; no task is created |
| Priority urgent | Clear allowed-priority message |
| Date 09/30/2026 | Clear YYYY-MM-DD format message |
| Complete the same one-time task twice | Second attempt is rejected as already completed |
| Remove task and answer n | Removal is canceled and task remains |
| Upcoming days 0 | Valid boundary value should show tasks due today only |
| Upcoming days -1 | Rejected because the value must be positive |

## Local Verification Performed

The code was compiled with:

```
javac --release 17 -d out17 <all Java source files>
```

A console demonstration was run using sample data. The output showed polymorphic task listing and a recurring task whose due date moved forward after completion.

## Screenshot Checklist for Final Report

Take screenshots showing:
1. Startup/menu and sample data loaded.
2. Task list with both one-time and recurring tasks.
3. Successful completion of a recurring task and changed due date.
4. One invalid input case.
5. One invalid operation case, such as a missing task ID or completing a finished one-time task.
6. Statistics or another main feature.
