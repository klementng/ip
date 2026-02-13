package pixie.task;

import pixie.exceptions.TaskDeserializationException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String toCSV() {
        return String.format("%s", super.getBaseCSV());
    }

    public static Todo fromCSV(String[] parts) throws TaskDeserializationException {
        if (!parts[0].equals(Todo.class.getName())) {
            throw new TaskDeserializationException("Wrong format for todo task");
        }

        if (parts.length != 3) {
            throw new TaskDeserializationException("Wrong number of args for todo task");
        }

        try {
            boolean isCompleted = Boolean.parseBoolean(parts[1]);
            String description = parts[2];

            Todo todo = new Todo(description);
            if (isCompleted) {
                todo.markCompleted();
            }
            return todo;
        } catch (Exception e) {
            throw new TaskDeserializationException("An error occurred while deserializing for Todo: " + e);
        }

    }

}
