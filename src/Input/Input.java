package Input;

import Values.Exceptions.PNotPrimeException;
import Values.ZmodP;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    private Scanner scanner;
    private ArrayList<OperationInterface> operations;
    final private String inputPrefix = " < ";
    final private String outputPrefix = " > ";

    public Input(Scanner scanner) {
        this.scanner = scanner;
        operations = new ArrayList<>();
    }

    public Input addOperation(OperationInterface o) {
        operations.add(o);

        return this;
    }

    public void read() throws IllegalStateException {
        if (this.scanner==null) {
            throw new IllegalStateException("Input_Scanner.read: scanner should be not null");
        }

        System.out.println(outputPrefix + "First reading the desired operation to be executed");
        System.out.println(outputPrefix + "Supported operations are:");
        for (OperationInterface s : operations) {
            System.out.println("\t- '" + s.getCommand() + "'");
        }
        System.out.println(inputPrefix + "Please enter the desired operation.");

        StringBuilder regex = new StringBuilder();
        boolean first = true;
        for (OperationInterface s : operations) {
            if (first) {
                first = false;
            } else {
                regex.append('|');
            }

            regex.append('(').append(s.getCommand()).append(')');
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

    public String getInputPrefix() {
        return inputPrefix;
    }

    public String getOutputPrefix() {
        return outputPrefix;
    }

    private void executeOperation(String operation) {
        // Find the operation we want to execute in the list with operations.
        for (OperationInterface o : operations) {
            if (o.getCommand().equals(operation)) {
                o.execute();
            }
        }
    }

    ZmodP readZmodP(String name) {
        System.out.println(inputPrefix + "[" + name + "] Please enter a Values.ZmodP value in the format 'Z mod P' with Z an integer and P a prime.");

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
            } catch (PNotPrimeException e) {
                System.out.println("P should be prime, please try again.");
            }
        } while (true);
    }

    void readPolynomial() {
        // Implement this
    }

    void readField() {
        // Implement this
    }
}