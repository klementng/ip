package pixie.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.task.Task;
import pixie.ui.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_PREFIX = "mark";
    private static final Pattern ARG_REGEX = Pattern.compile("^mark\\s+(?<number>\\d+?)");

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager manager, Ui ui) {
        try {
            Task task = manager.markCompleted(this.index);
            ui.showResponse("Nice! I've marked this task as done: %s\n", task);
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static MarkCommand parse(String line) throws CommandParseException {
        Matcher matcher = ARG_REGEX.matcher(line);

        if (!matcher.matches()) {
            throw new CommandParseException("Invalid format given. Expected: mark <task number>");
        }

        int index = Integer.parseInt(matcher.group("number")) - 1;
        return new MarkCommand(index);
    }

}
