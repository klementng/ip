package pixie.ui;

public class Ui {

    public void showIntro() {
        System.out.println("Hello! I'm Pixie");
        System.out.println("What can I do for you?");
    }

    public void showPrompt() {
        System.out.print("\n\n>>> ");
    }

    public void showErrorMessage(String message) {
        System.out.printf("ERROR: %s\n", message);
    }

    public void showErrorMessage(Exception e) {
        System.out.printf("ERROR: %s\n", e.getMessage());
    }

    public void showErrorMessage(String format, Object... args) {
        System.out.printf("ERROR:" + format, args);
    }

    public void showStatusMessage(String message) {
        System.out.printf("STATUS: %s\n", message);
    }

    public void showResponse(String message) {
        System.out.printf("%s\n", message);
    }

    public void showResponse(String format, Object... args) {
        System.out.printf(format, args);
    }

}
