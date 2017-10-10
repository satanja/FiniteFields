import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {

    private Scanner scanner;
    private String[] supportedOperations = {"abc", "def"};

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public void read() throws IllegalStateException {
        if (this.scanner==null) {
            throw new IllegalStateException("Input_Scanner.read: scanner should be not null");
        }

        System.out.println("First reading the desired operation to be executed");
        System.out.println("Supported operations are:");
        for (String s : supportedOperations) {
            System.out.println("\t- '" + s + "'");
        }

        StringBuilder regex = new StringBuilder();
        boolean first = true;
        for (String s : supportedOperations) {
            if (first) {
                first = false;
            } else {
                regex.append('|');
            }

            regex.append('(').append(s).append(')');
        }

        Pattern compile = Pattern.compile(regex.toString());

        String operation = null;

        while (operation == null) {
            try {
                operation = scanner.next(compile);
            } catch (InputMismatchException e) {
                System.out.println("Operation is not supported, please try again.");
                scanner.nextLine();
            }
        }

        System.out.println("Operation: [" + operation + "]");
    }
}