package pixie.command;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_PREFIX = "bye";

    @Override
    public void execute(TaskManager manager, Ui ui) {
        ui.showStatusMessage("Saving...");
        manager.save();
        System.exit(0);
    }

    public static ExitCommand parse(String line) throws CommandParseException {
        return new ExitCommand();
    }
}
