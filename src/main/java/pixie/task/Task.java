package pixie.task;

/**
 * Represents a task with a description and completion status. A task can be
 * marked as completed or incomplete, and provides a string representation
 * showing its current state.
 */
public class Task {

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

}
