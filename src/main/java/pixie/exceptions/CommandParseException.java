package pixie.exceptions;

public class CommandParseException extends RuntimeException {

    public CommandParseException() {
        super();
    }

    public CommandParseException(String message) {
        super(message);
    }
}
