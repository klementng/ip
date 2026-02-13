package pixie.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.task.Task;
import pixie.task.Todo;
import pixie.ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_PREFIX = "todo";
    private static final Pattern ARG_REGEX = Pattern.compile("^todo\\s+(?<desc>.+?)");
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskManager manager, Ui ui) {
        Task item = new Todo(description);
        manager.addTask(item);
    }

    public static TodoCommand parse(String line) throws CommandParseException {
        Matcher matcher = ARG_REGEX.matcher(line);

        if (!matcher.matches()) {
            throw new CommandParseException("Invalid format given. Expected: todo <desc>");
        }

        return new TodoCommand(matcher.group("desc"));
    }
}
