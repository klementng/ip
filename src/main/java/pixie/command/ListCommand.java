package pixie.command;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_PREFIX = "list";

    @Override
    public void execute(TaskManager manager, Ui ui) {
        manager.printTasks();
    }

    public static ListCommand parse(String line) throws CommandParseException {
        return new ListCommand();
    }
}
