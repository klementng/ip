import java.util.Scanner;

public class Pixie {

    public static void main(String[] args) {
        TaskManager tasks = new TaskManager();
        Scanner stdin = new Scanner(System.in);

        System.out.println("Hello! I'm Pixie");
        System.out.println("What can I do for you?");

        while (true) {
            System.out.print("\n>>> ");

            String text = stdin.nextLine().strip();
            String command = text.toLowerCase();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                tasks.printTasks();
            } else {
                System.out.printf("added: %s ", text);
                tasks.addTask(text);
            }
        }
    }
}
