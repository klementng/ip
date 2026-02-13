package pixie.ui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixie.helper.TaskManager;
import pixie.task.Deadline;
import pixie.task.Event;
import pixie.task.Task;
import pixie.task.Todo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pixie {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?<cmd>\\S+)(?:\\s+(?<args>.*))?");
    private static final Pattern DEADLINE_ARGS_PATTERN = Pattern.compile("(?<desc>.+?)\\s+/by\\s+(?<date>.+)");
    private static final Pattern EVENT_ARGS_PATTERN = Pattern
            .compile("(?<desc>.+?)\\s+/from\\s+(?<start>.+?)\\s+/to\\s+(?<end>.+)");

    private static final Path SAVE_PATH = Paths.get("data/", "save.csv");

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        System.err.println(SAVE_PATH.toAbsolutePath());

        try {
            manager.load(SAVE_PATH);
        } catch (IOException e) {
            System.err.println("Error occurred while loading save file!");
        }

        try {

            Scanner stdin = new Scanner(System.in);

            System.out.println("Hello! I'm Pixie");
            System.out.println("What can I do for you?");

            while (true) {
                System.out.print("\n\n>>> ");
                String input = stdin.nextLine().strip();

                Matcher matcher = COMMAND_PATTERN.matcher(input);
                if (!matcher.matches()) {
                    System.out.println("Please enter a valid command!");
                    continue;
                }

                String command = matcher.group("cmd").toLowerCase();
                String arguments = matcher.group("args");

                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (command.equalsIgnoreCase("list")) {
                    manager.printTasks();
                } else if (command.equalsIgnoreCase("todo")) {
                    handleTodo(manager, arguments);
                } else if (command.equalsIgnoreCase("delete")) {
                    handleDelete(manager, arguments);
                } else if (command.equalsIgnoreCase("deadline")) {
                    handleDeadline(manager, arguments);
                } else if (command.equalsIgnoreCase("event")) {
                    handleEvent(manager, arguments);
                } else if (command.equalsIgnoreCase("mark")) {
                    handleMarking(manager, arguments, true);
                } else if (command.equalsIgnoreCase("unmark")) {
                    handleMarking(manager, arguments, false);
                } else if (command.equalsIgnoreCase("delete")) {
                    handleDelete(manager, arguments);
                } else {
                    System.out
                            .println("Please enter a valid command! (list|todo|deadline|event|mark|unmark|delete|bye)");
                }
            }

        } finally {

            try {
                System.out.println("Saving...");
                manager.save(SAVE_PATH);
            } catch (IOException e) {
                System.out.println("Error occurred while saving..." + e);
            }

        }
    }

    private static void handleEvent(TaskManager manager, String arguments) {
        if (arguments == null) {
            System.out.println("Invalid number of arguments! Expected: event <desc> /from <start> /to <end>");
            return;
        }

        Matcher matcher = EVENT_ARGS_PATTERN.matcher(arguments);
        if (!matcher.matches()) {
            System.out.println("Invalid format given. Expected: event <desc> /from <start> /to <end>");
            return;
        }

        String desc = matcher.group("desc");
        String from = matcher.group("start");
        String to = matcher.group("end");

        Task item = new Event(desc, from, to);
        manager.addTask(item);

        System.out.printf("Got it! added: %s \n", item);
        System.out.printf("There are/is %d item(s) in the list", manager.getTaskCount());
    }

    private static void handleDeadline(TaskManager manager, String arguments) {
        if (arguments == null) {
            System.out.println("Invalid number of arguments! Expected: deadline <desc> /by <date>");
            return;
        }

        Matcher matcher = DEADLINE_ARGS_PATTERN.matcher(arguments);

        if (!matcher.matches()) {
            System.out.println("Invalid format given. Expected: deadline <desc> /by <date>");
            return;
        }

        String desc = matcher.group("desc");
        String by = matcher.group("date");

        Task item = new Deadline(desc, by);
        manager.addTask(item);
        System.out.printf("Got it! added: %s \n", item);
        System.out.printf("There are/is %d item(s) in the list", manager.getTaskCount());
    }

    private static void handleTodo(TaskManager manager, String arguments) {
        if (arguments == null || arguments.isBlank()) {
            System.out.println("Invalid number of arguments! Expected: todo <desc>");
            return;
        }

        Task item = new Todo(arguments);
        manager.addTask(item);
        System.out.printf("Got it! added: %s \n", item);
        System.out.printf("There are/is %d item(s) in the list", manager.getTaskCount());

    }

    private static void handleMarking(TaskManager manager, String arguments, boolean isCompleted) {
        if (arguments == null) {
            System.out.println("Invalid number of arguments! Expected task index.");
            return;
        }

        try {
            int index = Integer.parseInt(arguments) - 1;
            Task task;

            if (isCompleted) {
                task = manager.markCompleted(index);
                System.out.printf("Nice! I've marked this task as done: %s\n", task);

            } else {
                task = manager.markCompleted(index);
                System.out.printf("OK, I've marked this task as not done yet: %s\n", task);

            }

        } catch (NumberFormatException e) {
            System.out.println("Index entered is not a number!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    private static void handleDelete(TaskManager manager, String arguments) {
        if (arguments == null) {
            System.out.println("Invalid number of arguments! Expected task index.");
            return;
        }

        try {
            int index = Integer.parseInt(arguments) - 1;
            Task task = manager.deleteTask(index);
            System.out.printf("Noted! removed: %s \n", task);
            System.out.printf("There are/is %d item(s) in the list\n", manager.getTaskCount());

        } catch (NumberFormatException e) {
            System.out.println("Index entered is not a number!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

}