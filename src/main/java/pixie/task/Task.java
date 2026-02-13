package pixie.task;

import pixie.exceptions.TaskDeserializationException;

/**
 * Represents a task with a description and completion status. A task can be
 * marked as completed or incomplete, and provides a string representation
 * showing its current state.
 */
public abstract class Task {

    private String description;
    private boolean isCompleted;

    /**
     * Constructs a new Task with the specified description. The task is initially
     * marked as not completed.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Marks this task as completed
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks this task as incomplete
     */
    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of the task in the format "[X] description"
     *
     * @return a formatted string showing the completion status and description of
     *         the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isCompleted ? "X" : " ", this.description);
    }

    protected String getBaseCSV() {
        // this.getClass().getName() return runtime type.
        return String.format("%s,%s,%s", this.getClass().getName(), this.isCompleted, this.description);

    }

    /**
     * Converts the task to a CSV format string.to serialization purposes
     * 
     * @return a String representation of this task in CSV format
     */
    public abstract String toCSV();

    /**
     * Creates a Task object by parsing CSV format data. \
     *
     * @return a new Task object with data parsed from CSV format
     */

    public static Task fromCSV(String line) throws TaskDeserializationException {

        if (line == null || line.isBlank()) {
            return null;
        }

        String[] parts = line.split(",");

        if (parts[0].equals(Deadline.class.getName())) {
            return Deadline.fromCSV(parts);
        } else if (parts[0].equals(Todo.class.getName())) {
            return Todo.fromCSV(parts);
        } else if (parts[0].equals(Event.class.getName())) {
            return Event.fromCSV(parts);
        } else {
            throw new TaskDeserializationException("Unknown task line");
        }
    }

}
