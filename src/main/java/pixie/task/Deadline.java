package pixie.task;

import pixie.exceptions.TaskDeserializationException;

public class Deadline extends Task {

    private String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.dueBy);
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s", super.getBaseCSV(), this.dueBy);
    }

    public static Deadline fromCSV(String[] parts) {
        if (!parts[0].equals(Deadline.class.getName())) {
            throw new TaskDeserializationException("Wrong format for deadline");
        }

        if (parts.length != 4) {
            throw new TaskDeserializationException("Wrong number of args for deadline");
        }

        try {
            boolean isCompleted = Boolean.parseBoolean(parts[1]);
            String description = parts[2];
            String dueBy = parts[3];

            Deadline deadline = new Deadline(description, dueBy);
            if (isCompleted) {
                deadline.markCompleted();
            }
            return deadline;
        } catch (Exception e) {
            throw new TaskDeserializationException("An error occurred while deserializing for Todo: " + e);
        }
    }
}