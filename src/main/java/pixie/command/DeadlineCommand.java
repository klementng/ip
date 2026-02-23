package pixie.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.task.Deadline;
import pixie.task.Task;
import pixie.ui.Ui;

public class DeadlineCommand extends Command {
    public static final String COMMAND_PREFIX = "deadline";
    private static final Pattern ARG_PATTERN = Pattern.compile("^deadline\\s+(?<desc>.+?)\\s+/by\\s+(?<date>.+)");

    private String desc;
    private String by;

    public DeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskManager manager, Ui ui) {
        Task item = new Deadline(this.desc, this.by);
        manager.addTask(item);

        ui.showResponse("Got it! added: %s \n", item);
        ui.showResponse("There are/is %d item(s) in the list", manager.getTaskCount());
    }

    public static DeadlineCommand parse(String line) throws CommandParseException {
        Matcher matcher = ARG_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new CommandParseException("Invalid format given. Expected: deadline <desc> /by <date>");
        }

        String desc = matcher.group("desc");
        String by = matcher.group("date");

        return new DeadlineCommand(desc, by);
    }
}
