package pixie.task;

import pixie.exceptions.TaskDeserializationException;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s", super.getBaseCSV(), this.from, this.to);
    }

    public static Event fromCSV(String[] parts) {
        if (!parts[0].equals(Event.class.getName())) {
            throw new TaskDeserializationException("Wrong format for Event");
        }

        if (parts.length != 5) {
            throw new TaskDeserializationException("Wrong number of args for Event");
        }

        try {
            boolean isCompleted = Boolean.parseBoolean(parts[1]);
            String description = parts[2];
            String from = parts[3];
            String to = parts[4];

            Event event = new Event(description, from, to);

            if (isCompleted) {
                event.markCompleted();
            }
            return event;
        } catch (Exception e) {
            throw new TaskDeserializationException("An error occurred while deserializing for Event: " + e);
        }
    }
}
