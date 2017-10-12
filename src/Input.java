import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    private Scanner scanner;
    private String[] supportedOperations = {"abc", "def", "mul"};
    final private String inputPrefix = " < ";
    final private String outputPrefix = " > ";

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public void read() throws IllegalStateException {
        if (this.scanner==null) {
            throw new IllegalStateException("Input_Scanner.read: scanner should be not null");
        }

        System.out.println(outputPrefix + "First reading the desired operation to be executed");
        System.out.println(outputPrefix + "Supported operations are:");
        for (String s : supportedOperations) {
            System.out.println("\t- '" + s + "'");
        }
        System.out.println(inputPrefix + "Please enter the desired operation.");

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
                System.out.println(inputPrefix + "Operation is not supported, please try again.");
                scanner.nextLine();
            }
        }

        System.out.println(outputPrefix + "Operation selected: [" + operation + "]");

        executeOperation(operation);
    }

    private void executeOperation(String operation) {
        switch (operation) {
            case "abc":

                System.out.println("Do abc");
                break;
            case "def":
                System.out.println("Do def");
                break;
            case "mul":
                System.out.println(outputPrefix + "Expects two ZmodP objects a and b as input with equal p.");
                System.out.println(outputPrefix + "Returns a ZmodP value object with value (a * b) mod p");

                ZmodP a = readZmodP("a");
                ZmodP b = readZmodP("b");

                try {
                    ZmodP result = a.multiply(b);
                    System.out.println(outputPrefix + result.toString());
                } catch (IllegalArgumentException e) {
                    System.out.println(outputPrefix + "FAILED: The p values of both ZmodP objects should be equal.");
                }

                break;
        }
    }

    private ZmodP readZmodP(String name) {
        System.out.println(inputPrefix + "[" + name + "] Please enter a ZmodP value in the format 'Z mod P' with Z an integer and P a prime.");

        Pattern pattern = Pattern.compile("[ 0]*([1-9]\\d*) *[mod]{1,3} *[ 0]*([1-9]\\d*)");

        Matcher matches = null;
        do {
            try {
                String nextLine = scanner.nextLine();

                matches = pattern.matcher(nextLine);

                // Skip empty lines.
                if (nextLine.equals("")) {
                    continue;
                } else if (! matches.matches()) { // Check if the input matches the expected input.
                    System.out.println(inputPrefix + "Illegal input given, please try again and mind the format.");
                    continue;
                }

                int value = Integer.valueOf(matches.group(1));
                int p = Integer.valueOf(matches.group(2));

                return new ZmodP(value, p);
            } catch (IllegalArgumentException e) {
                System.out.println("P should be prime, please try again.");
            }
        } while (true);
    }

    private void readPolynomial() {
        // Implement this
    }

    private void readField() {
        // Implement this
    }
}