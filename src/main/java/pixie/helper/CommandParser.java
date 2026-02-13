package pixie.helper;

import pixie.command.Command;
import pixie.command.DeadlineCommand;
import pixie.command.DeleteCommand;
import pixie.command.EventCommand;
import pixie.command.ExitCommand;
import pixie.command.ListCommand;
import pixie.command.MarkCommand;
import pixie.command.TodoCommand;
import pixie.command.UnmarkCommand;
import pixie.exceptions.CommandParseException;

public class CommandParser {

    public Command parse(String line) throws CommandParseException {

        if (line.startsWith(ExitCommand.COMMAND_PREFIX)) {
            return ExitCommand.parse(line);
        } else if (line.startsWith(ListCommand.COMMAND_PREFIX)) {
            return ListCommand.parse(line);
        } else if (line.startsWith(TodoCommand.COMMAND_PREFIX)) {
            return TodoCommand.parse(line);
        } else if (line.startsWith(DeadlineCommand.COMMAND_PREFIX)) {
            return DeadlineCommand.parse(line);
        } else if (line.startsWith(EventCommand.COMMAND_PREFIX)) {
            return EventCommand.parse(line);
        } else if (line.startsWith(MarkCommand.COMMAND_PREFIX)) {
            return MarkCommand.parse(line);
        } else if (line.startsWith(UnmarkCommand.COMMAND_PREFIX)) {
            return UnmarkCommand.parse(line);
        } else if (line.startsWith(DeleteCommand.COMMAND_PREFIX)) {
            return DeleteCommand.parse(line);
        } else {
            throw new CommandParseException("Please enter a valid command!");
        }

    }
}
