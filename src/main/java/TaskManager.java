import java.util.ArrayList;

/**
 * A simple task manager that maintains a list of tasks as strings. Provides
 * functionality to add tasks and display all tasks in a numbered list.
 */
public class TaskManager {

    private ArrayList<String> tasks;

    /**
     * Constructs a new TaskManager with an empty task list.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list. Skipped if item is a empty string or null.
     * 
     * @param item the task description to be added
     */
    public void addTask(String item) {
        if (item == null || item.length() == 0) {
            return;
        }

        tasks.add(item);
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
