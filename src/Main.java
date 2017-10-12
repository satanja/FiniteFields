import Input.Input;
import Input.Operations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Scanner scanner = null;

        if (args.length == 0) {
            scanner = new Scanner(System.in);
        } else {
            File file;
            String filePath = args[1];

            // Retrieve the file.
            file = new File(filePath);

            // Check if the file exists and is a directory.
            if (!file.exists()) {
                System.out.println("File does not exist.");
            } else if (file.isDirectory()) {
                System.out.println("Folder selected instead of a file, please give a valid file.");
            } else {
                // Found a valid file.
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    System.out.println("An exception was raised during loading the file specified: " + e.getMessage());
                }
            }
        }

        if (scanner == null) {
            return;
        }

        Input input = new Input(scanner);

        // Add all the supported operations.
        input
            .addOperation(new AddZmodP(input))
            .addOperation(new SubZmodP(input))
            .addOperation(new MultiplyZmodP(input))
            .addOperation(new RemainderZmodP(input))
            .addOperation(new DivZmodP(input));

        input.read();
    }

}
