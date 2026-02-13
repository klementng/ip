package pixie.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.task.Task;
import pixie.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_PREFIX = "delete";
    private static final Pattern ARG_PATTERN = Pattern.compile("^delete\\s+(?<number>d+?)");

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager manager, Ui ui) {
        try {
            Task task = manager.deleteTask(index);
            ui.showResponse("Noted! removed: %s \n", task);
            ui.showResponse("There are/is %d item(s) in the list\n", manager.getTaskCount());

        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static DeleteCommand parse(String line) throws CommandParseException {
        Matcher matcher = ARG_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new CommandParseException("Invalid format given. Expected: delete <task number>");
        }

        int index = Integer.parseInt(matcher.group("number")) - 1;
        return new DeleteCommand(index);
    }

}
