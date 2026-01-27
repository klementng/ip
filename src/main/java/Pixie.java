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
            } else if (command.equals("list")) {
                manager.printTasks();
            } else if (command.equals("mark") || command.equals("unmark")) {

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

                if (command.equals("mark")) {
                    task = manager.markCompleted(index);

                    if (task == null) {
                        System.out.println("Invalid Task Number entered!");
                        continue;
                    }
                    System.out.printf("Nice! I've marked this task as done: %s\n", task);

                } else {

                    task = manager.markCompleted(index);

                    if (task == null) {
                        System.out.println("Invalid Task Number entered!");
                        continue;
                    }
                    System.out.printf("OK, I've marked this task as not done yet: %s\n", task);

                }

            } else {
                System.out.printf("added: %s ", text);
                manager.addTask(text);
            }
        }
    }
}
