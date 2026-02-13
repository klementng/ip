package pixie.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.exceptions.CommandParseException;
import pixie.helper.TaskManager;
import pixie.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_PREFIX = "find";
    private static final Pattern ARGS_PATTERN = Pattern.compile("^find\\s+(?<term>.+?)");

    private String term;

    public FindCommand(String term) {
        this.term = term;
    }

    @Override
    public void execute(TaskManager manager, Ui ui) {
        manager.filterPrintTasks(this.term);
    }

    public static FindCommand parse(String line) throws CommandParseException {
        Matcher matcher = ARGS_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new CommandParseException("Invalid format given. Expected: find <term>");
        }

        String term = matcher.group("term");

        return new FindCommand(term);
    }
}
