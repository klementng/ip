import java.util.ArrayList;

/**
 * A task manager that maintains a list of tasks
 */
public class TaskManager {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskManager with an empty task list.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list. Skipped if item is a empty string or null.
     * 
     * @param item the task to be added
     */
    public void addTask(Task item) {
        if (item == null) {
            return;
        }

        this.tasks.add(item);
    }

    /**
     * Retrieves a task at the specified index from the task list.
     *
     * @param index the zero-based index of the task to retrieve
     * @return the Task object at the specified index, or null if the index is out
     *         of bounds or the task list is empty
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (this.tasks.size() == 0) {
            throw new IndexOutOfBoundsException("The task list is empty");
        }

        if (index < 0 || index >= this.tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid Task index given");
        }

        return this.tasks.get(index);
    }

    /**
     * Retrieves a the number of item in the list
     * 
     * @return size of the task list
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Marks a task at the specified index as completed.
     * 
     * @param index the zero-based index of the task to mark as completed
     * @return the task that was marked as completed, or null if the index is
     *         invalid or the task does not exist
     */
    public Task markCompleted(int index) throws IndexOutOfBoundsException {
        Task task = this.getTask(index);

        task.markCompleted();
        return task;
    }

    /**
     * Marks a task at the specified index as incomplete.
     * 
     * @param index the zero-based index of the task to mark as incomplete
     * @return the task that was marked as incomplete, or null if the index is
     *         invalid
     */
    public Task markIncomplete(int index) throws IndexOutOfBoundsException {
        Task task = this.getTask(index);

        task.markIncomplete();
        return task;
    }

    /**
     * Prints all tasks in the list with numbering
     */
    public void printTasks() {

        if (tasks.size() == 0) {
            System.out.println("<< EMPTY >>");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }

        }

    }

}
