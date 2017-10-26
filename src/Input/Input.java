package Input;

import Values.Exceptions.PNotPrimeException;
import Values.FiniteField;
import Values.Monomial;
import Values.Polynomial;
import Values.ZmodP;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    public static final int MAX_PRIME_VALUE = 109;
    public static final int MAX_DEGREE = 9;
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

        printOutput("First reading the desired operation to be executed");
        printOutput("Supported operations are:");
        for (OperationInterface s : operations) {
            System.out.println("\t- '" + s.getCommand() + "'");
        }
        printInput("Please enter the desired operation.");

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
                printInput("Operation is not supported, please try again.");
                scanner.nextLine();
            }
        }

        printOutput("Operation selected: [" + operation + "]");

        executeOperation(operation);
    }

    public void printInput(String input) {
        System.out.println(inputPrefix + input);
    }

    public void printOutput(String output) {
        System.out.println(outputPrefix + output);
    }

    private void executeOperation(String operation) {
        // Find the operation we want to execute in the list with operations.
        for (OperationInterface o : operations) {
            if (o.getCommand().equals(operation)) {
                o.execute();
            }
        }
    }

    int readInt(String name) {
        printInput("[" + name + "] Please enter an integer value.");

        Integer result = null;
        while (result==null) {
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                printOutput("Illegal input for integer given, please try again");
            }
        }

        printOutput("Successfully read integer " + name + ", as " + name + " = " + result);

        return result;
    }

    int readPrime(String name) {
        printInput("[" + name + "] Please enter an integer value which is prime.");

        Integer result = null;

        while (result==null) {
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                printOutput("Illegal input for integer given, please try again");
                continue;
            }

            // Ensure that the input is prime.
            if (! ZmodP.isPrime(result)) {
                printOutput("Input is not prime, please try again.");
                result = null;
                continue;
            }

            if (result > MAX_PRIME_VALUE) {
                printOutput("Prime should be smaller than " + MAX_PRIME_VALUE + ", please try again.");
                result = null;
                continue;
            }
        }

        printOutput("Successfully read prime " + name + ", as " + name + " = " + result);

        return result;
    }

    ZmodP readZmodP(String name) {
        printInput("[" + name + "] Please enter a Values.ZmodP value in the format 'Z mod P' with Z an integer and P a prime.");

        Pattern pattern = Pattern.compile("[ 0]*([1-9]\\d*) *[mod]{1,3} *[ 0]*([1-9]\\d*)");

        Matcher matches;
        do {
            try {
                String nextLine = scanner.nextLine();

                matches = pattern.matcher(nextLine);

                // Skip empty lines.
                if (nextLine.equals("")) {
                    continue;
                } else if (! matches.matches()) { // Check if the input matches the expected input.
                    printInput("Illegal input given, please try again and mind the format.");
                    continue;
                }

                int value = Integer.valueOf(matches.group(1));
                int p = Integer.valueOf(matches.group(2));

                ZmodP result = new ZmodP(value, p);
                printOutput("Successfully ready ZmodP value " + name + ", with " + name + " = " + result);

                return result;
            } catch (PNotPrimeException e) {
                printOutput("P should be prime, please try again.");
            }
        } while (true);
    }

    Polynomial readPolynomial(String name) {
        printInput("[" + name + "] Please enter a Values.PolyPoly value in the format 'c_0 X e_0 + c_1 X e_1 + ... + c_n X e_n mod P' with P as a prime and for all c_i and e_i as integer values, with i from 0 to n.");
        printInput("Note: For the input notation can spaces and ones be omitted.");

        Polynomial result = null;
        String firstCheckValidInput = "(?:\\s*(\\d*)(?:\\s*[Xx]\\s*(\\d)*)?)(?:(?:\\s*\\+)(?:\\s*(\\d*)(?:\\s*[Xx]\\s*(\\d*))?)*)*\\s*[mod]{1,3}\\s*(\\d+)";

        while (result == null) {
            String line = scanner.nextLine().toLowerCase();
            line = line.replaceAll("\\s", ""); // Remove all whitespaces.


            if (line.equals("")) continue; // Skip empty lines.

            // Do some initial checking if the string has things like a "mod {int}" part, no monomials preceding "mod", and so on.
            if (! line.matches(firstCheckValidInput)) {
                printOutput("Invalid input given, please check if it satisfies the syntax and try again.");
                continue;
            }

            String[] parts = line.split("mod");
            String polynomials = parts[0];
            String modDigitAsString = parts[1];

            // Retrieve the mod digit.
            int mod;
            try {
                mod = stringWithWhitespaceToInt(modDigitAsString);
            } catch (IllegalArgumentException e) {
                printOutput("Modulo digit not detected, please check your syntax and try again.");
                continue;
            }

            if (! ZmodP.isPrime(mod)) {
                printOutput("Modulo should be prime.");
                continue;
            }

            // Retrieve the monomials.
            String[] monomialsString = polynomials.split("\\+");
            Map<Integer, Monomial> monomials = new HashMap<>();
            boolean readMonomialsSuccessful = true;
            for(String stringedMonomial : monomialsString) {
                if (! readMonomialsSuccessful) {
                    break;
                }

                String[] splitted = stringedMonomial.split("x");
                int c;
                int e;

                switch (splitted.length) {
                    case 0: // Apparently the empty string was passed.
                        if (stringedMonomial.length() == 0) {
                            // Skip the empty string
                            continue;
                        } else { // Else only "x" was passed.
                            c = 1;
                            e = 1;
                            break;
                        }
                    case 1: // Either the digit preceding or succeeding the x is not passed. Check which one it was.
                        int i = stringedMonomial.indexOf("x");
                        if (i == 0) { // Check if the preceding digit was not passed.
                            c = 1;
                            e = stringWithWhitespaceToInt(splitted[0]);
                        } else if (i == stringedMonomial.length() - 1) { // Check if the succeeding digit was not passed.
                            c = stringWithWhitespaceToInt(splitted[0]);
                            e = 1;
                        } else if (i == -1) { // No x was passed.
                            c = stringWithWhitespaceToInt(stringedMonomial);
                            e = 0;
                        } else { // This should not happen.
                            printOutput("Something went wrong when reading your polynomial, please check the syntax and try again.");
                            readMonomialsSuccessful = false;
                            continue;
                        }
                        break;
                    case 2: // Both arguments were passed.
                        c = stringWithWhitespaceToInt(splitted[0]);
                        e = stringWithWhitespaceToInt(splitted[1]);
                        break;
                    default: // This should never happen.
                        readMonomialsSuccessful = false;
                        printOutput("Something went wrong when reading your polynomial, please check the syntax and try again.");
                        continue;
                }


                // Ensure
                if (e > MAX_DEGREE) {
                    printOutput("The degree should not exceed " + MAX_DEGREE + ", pelase try again.");
                    readMonomialsSuccessful = false;
                    break;
                }

                // Retrieve the monomial with the same exponent, if it was already set.
                Monomial m = monomials.get(e);

                if (m != null) { // Merge the values of both monomials if one with the same exponent was already present.
                    c += m.getCoefficient().getValue();
                }

                m = new Monomial(new ZmodP(c, mod), e);
                monomials.put(e, m);
            }

            // Check if the monomial was read successfully, if not allow the user to retry.
            if (!readMonomialsSuccessful) {
                continue;
            }

            Monomial[] monomialsAsArray = new Monomial[monomials.size()];
            monomials.values().toArray(monomialsAsArray);

            result = new Polynomial(monomialsAsArray, new ZmodP(0, mod));
        }

        printOutput("Successfully read polynomial " + name + ", as " + name + " = " + result.toString());
        return result;
    }

    FiniteField readField(String name) {
        printInput("[" + name + "] Please enter the value of the field, it is constructed of two polynomials x and y, and an integer i.");

        Polynomial g = readPolynomial(name + "_x");
        int field = readInt(name + "_i");
        Polynomial a = readPolynomial(name + "_y");

        return new FiniteField(g, new ZmodP(0, field), a);
    }

    /**
     * Removes all whitespace from a string and then tries to convert it into an integer.
     *
     * @throws IllegalArgumentException If the input could not be converted into an integer.
     * @param s The string to be stripped.
     * @return The integer value.
     */
    protected int stringWithWhitespaceToInt(String s) throws IllegalArgumentException {
        s = s.replaceAll("\\s", "");
        int result;

        try {
            result = Integer.valueOf(s);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException();
        }

        return result;
    }
}