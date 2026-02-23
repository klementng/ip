package pixie.ui;

/**
 * The Ui class handles all user interface interactions and output messages for
 * the Pixie application.
 * 
 * All output is directed to the System.out
 */
public class Ui {

    /**
     * Displays the introduction message
     */
    public void showIntro() {
        System.out.println("Hello! I'm Pixie");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the command prompt to the user. Prints a newline followed by the
     * prompt symbol ">>> " to indicate that the system is ready to accept user
     * input.
     */
    public void showPrompt() {
        System.out.print("\n\n>>> ");
    }

    /**
     * Displays an error message to the user.
     * 
     * @param message the error message to be displayed
     */
    public void showErrorMessage(String message) {
        System.out.printf("ERROR: %s\n", message);
    }

    /**
     * Displays an error message to the user based on the provided exception.
     * 
     * @param e the Exception whose message will be displayed as an error
     */
    public void showErrorMessage(Exception e) {
        System.out.printf("ERROR: %s\n", e.getMessage());
    }

    /**
     * Displays an error message to the user with formatted output.
     * 
     * @param format the format string for the error message
     * @param args   the arguments to be substituted into the format string
     */
    public void showErrorMessage(String format, Object... args) {
        System.out.printf("ERROR:" + format, args);
    }

    /**
     * Displays a status message to the user.
     * 
     * @param message the status message to be displayed
     */
    public void showStatusMessage(String message) {
        System.out.printf("STATUS: %s\n", message);
    }

    /**
     * Displays a response message to the user through standard output.
     *
     * @param message the message to be displayed to the user
     */
    public void showResponse(String message) {
        System.out.printf("%s\n", message);
    }

    /**
     * Displays a formatted response message to the user.
     *
     * @param format the format string to be used for output
     * @param args   variable number of arguments to be formatted
     */
    public void showResponse(String format, Object... args) {
        System.out.printf(format, args);
    }

}
