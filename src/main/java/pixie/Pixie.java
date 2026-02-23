package pixie;

import java.util.Scanner;

import pixie.command.Command;
import pixie.exceptions.CommandParseException;
import pixie.helper.CommandParser;
import pixie.helper.TaskManager;

import pixie.ui.Ui;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pixie {
    private static final Path SAVE_PATH = Paths.get("data/", "save.csv");

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskManager manager = new TaskManager(ui, SAVE_PATH);
        CommandParser parser = new CommandParser();

        Scanner stdin = new Scanner(System.in);

        manager.load();
        ui.showIntro();

        while (true) {
            ui.showPrompt();
            String line = stdin.nextLine().strip();

            try {
                Command command = parser.parse(line);
                command.execute(manager, ui);
            } catch (CommandParseException e) {
                ui.showErrorMessage(e.getMessage());
            } catch (Exception e) {
                ui.showErrorMessage("An unexpected error has occurred");
            }
        }

    }

}