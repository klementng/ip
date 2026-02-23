package pixie.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.task.Event;
import pixie.task.Task;
import pixie.ui.Ui;

public class EventCommand extends Command {

    public static final String COMMAND_PREFIX = "event";
    private static final Pattern ARGS_PATTERN = Pattern
            .compile("^event\\s+(?<desc>.+?)\\s+/from\\s+(?<start>.+?)\\s+/to\\s+(?<end>.+)");

    private String description;
    private String start;
    private String end;

    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskManager manager, Ui ui) {
        Task item = new Event(this.description, this.start, this.end);
        manager.addTask(item);

        ui.showResponse("Got it! added: %s \n", item);
        ui.showResponse("There are/is %d item(s) in the list", manager.getTaskCount());
    }

    public static EventCommand parse(String line) throws CommandParseException {
        Matcher matcher = ARGS_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new CommandParseException("Invalid format given. Expected: event <desc> /from <start> /to <end>");
        }

        String desc = matcher.group("desc");
        String from = matcher.group("start");
        String to = matcher.group("end");

        return new EventCommand(desc, from, to);
    }
}
