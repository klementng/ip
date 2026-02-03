import java.util.Arrays;
import java.util.Scanner;

public class Pixie {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Scanner stdin = new Scanner(System.in);

        System.out.println("Hello! I'm Pixie");
        System.out.println("What can I do for you?");

        while (true) {
            System.out.print("\n\n>>> ");

            String text = stdin.nextLine().strip();

            args = text.split(" ");
            String command = args[0].toLowerCase();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("todo")) {
                if (args.length < 2) {
                    System.out.println("Invalid number of arguments! Minimum 2 expected");
                    continue;
                }
                Task item = new Todo(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                manager.addTask(item);
                System.out.printf("Got it! added: %s \n", item);
                System.out.printf("There are/is %d item(s) in the list", manager.getTaskCount());

            } else if (command.equals("event")) {
                if (args.length < 5) {
                    System.out.println("Invalid number of arguments! Minimum 5 expected");
                    continue;
                }

                String desc = "";
                String to = "";
                String from = "";

                int parseType = 0;

                for (int i = 1; i < args.length; i++) {
                    if (args[i].equalsIgnoreCase("/from")) {
                        parseType = 1;
                        continue;
                    }

                    if (args[i].equalsIgnoreCase("/to")) {
                        parseType = 2;
                        continue;
                    }

                    if (parseType == 0) {
                        desc += args[i];
                    } else if (parseType == 1) {
                        from += args[i];
                    } else {
                        to += args[i];
                    }

                }

                if (to.equals("") || from.equals("")) {
                    System.out.println("Invalid format given");
                    continue;
                }

                Task item = new Event(desc, from, to);
                manager.addTask(item);
                System.out.printf("Got it! added: %s \n", item);
                System.out.printf("There are/is %d item(s) in the list", manager.getTaskCount());

            } else if (command.equals("deadline")) {
                if (args.length < 4) {
                    System.out.println("Invalid number of arguments! Minimum 4 expected");
                    continue;
                }

                String desc = "";
                String dueDate = "";

                int parseType = 0;

                for (int i = 1; i < args.length; i++) {
                    if (args[i].equalsIgnoreCase("/by")) {
                        parseType = 1;
                        continue;
                    }

                    if (parseType == 0) {
                        desc += args[i];
                    } else {
                        dueDate += args[i];
                    }

                }

                if (dueDate.equals("")) {
                    System.out.println("Invalid format given");
                    continue;
                }

                Task item = new Deadline(desc, dueDate);
                manager.addTask(item);
                System.out.printf("Got it! added: %s \n", item);
                System.out.printf("There are/is %d item(s) in the list", manager.getTaskCount());

            } else if (command.equals("list")) {
                manager.printTasks();

            } else if (command.equals("mark")) {
                if (args.length != 2) {
                    System.out.println("Invalid number of arguments! Expected 2");
                    continue;
                }
                int index;
                Task task;

                try {
                    index = Integer.parseInt(args[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Index entered is not a number!");
                    continue;
                }
                task = manager.markCompleted(index);

                if (task == null) {
                    System.out.println("Invalid Task Number entered!");
                    continue;
                }
                System.out.printf("Nice! I've marked this task as done: %s\n", task);

            } else if (command.equals("unmark")) {

                if (args.length != 2) {
                    System.out.println("Invalid number of arguments! Expected 2");
                    continue;
                }
                int index;
                Task task;

                try {
                    index = Integer.parseInt(args[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Index entered is not a number!");
                    continue;
                }
                task = manager.markIncomplete(index);

                if (task == null) {
                    System.out.println("Invalid Task Number entered!");
                    continue;
                }
                System.out.printf("OK, I've marked this task as not done yet: %s\n", task);
            }

            else {
                System.out.printf("Please enter a valid command!");
            }
        }
    }
}
