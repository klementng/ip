import java.util.Scanner;

public class Pixie {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Pixie");
        System.out.println("What can I do for you?");

        Scanner stdin = new Scanner(System.in);
        System.out.print("\n>>> ");
        String line = stdin.nextLine();

        while (!line.equals("bye")) {
            System.out.println(line);
            System.out.print("\n>>> ");
            line = stdin.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
