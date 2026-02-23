# Pixie User Guide

**Pixie** is a command-line task management chatbot that helps you keep track of your todos, deadlines, and events.

## Quick Start

1. Ensure you have Java 17 installed on your computer.
2. Download the latest release of Pixie from the release tab on GitHub
3. Open a terminal and navigate to the folder containing the Pixie JAR file.
4. Run the application with: `java -jar pixie.jar`
5. You should see a welcome message:

   ```
   Hello! I'm Pixie
   What can I do for you?
   ```

6. Type commands at the `>>>` prompt and press Enter.

## Features

### Adding a todo: `todo`

Adds a simple todo task.

**Format:** `todo <description>`

**Example:**

```
>>> todo read book
```

**Expected output:**

```
Got it! added: [T][ ] read book
There are/is 1 item(s) in the list
```

### Adding a deadline: `deadline`

Adds a task with a specific deadline.

**Format:** `deadline <description> /by <date>`

**Example:**

```
>>> deadline return book /by Sunday
```

**Expected output:**

```
Got it! added: [D][ ] return book (by: Sunday)
There are/is 2 item(s) in the list
```

### Adding an event: `event`

Adds a task that spans a time period with a start and end.

**Format:** `event <description> /from <start> /to <end>`

**Example:**

```
>>> event project meeting /from Mon 2pm /to 4pm
```

**Expected output:**

```
Got it! added: [E][ ] project meeting (from: Mon 2pm to: 4pm)
There are/is 3 item(s) in the list
```

### Listing all tasks: `list`

Displays all tasks currently in your task list, with their index numbers.

**Format:** `list`

**Example:**

```
>>> list
```

**Expected output:**

```
1. [T][ ] read book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
```

### Marking a task as done: `mark`

Marks a task at the specified position as completed.

**Format:** `mark <task number>`

**Example:**

```
>>> mark 1
```

**Expected output:**

```
Nice! I've marked this task as done: [T][X] read book
```

### Unmarking a task: `unmark`

Marks a previously completed task as not done.

**Format:** `unmark <task number>`

**Example:**

```
>>> unmark 1
```

**Expected output:**

```
Nice! I've marked this task not done: [T][ ] read book
```

### Deleting a task: `delete`

Removes a task from the list at the specified position.

**Format:** `delete <task number>`

**Example:**

```
>>> delete 2
```

**Expected output:**

```
Noted! removed: [D][ ] return book (by: Sunday)
There are/is 2 item(s) in the list
```

### Finding tasks by keyword: `find`

Searches for all tasks whose description contains the given search term (case-insensitive).

**Format:** `find <term>`

**Example:**

```
>>> find book
```

**Expected output:**

```
1. [T][ ] read book
```

### Exiting the program: `bye`

Saves all tasks to disk and exits the application.
Your tasks are saved to `data/save.csv` and is automatically loaded the next time you start Pixie.

**Format:** `bye`

**Example:**

```
>>> bye
```

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| **Todo** | `todo <description>` | `todo read book` |
| **Deadline** | `deadline <description> /by <date>` | `deadline return book /by Sunday` |
| **Event** | `event <description> /from <start> /to <end>` | `event meeting /from Mon 2pm /to 4pm` |
| **List** | `list` | `list` |
| **Mark** | `mark <task number>` | `mark 1` |
| **Unmark** | `unmark <task number>` | `unmark 1` |
| **Delete** | `delete <task number>` | `delete 2` |
| **Find** | `find <term>` | `find book` |
| **Exit** | `bye` | `bye` |
