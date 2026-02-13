package pixie.command;

import pixie.helper.TaskManager;
import pixie.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskManager manager, Ui ui);

}
