import Input.Input;
import Input.Operations.FieldField.*;
import Input.Operations.Poly.*;
import Input.Operations.PolyInt.Scalar;
import Input.Operations.PolyPoly.*;
import Input.Operations.PolyPolyPoly.EquivalentInMod;
import Input.Operations.PrimePoly.AdditionTable;
import Input.Operations.PrimePoly.MultiplicationTable;
import Input.Operations.ZmodpZmodp.*;

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
                .addOperation(new DivZmodP(input))
                .addOperation(new AddPoly(input))
                .addOperation(new SubPoly(input))
                .addOperation(new MultiplyPoly(input))
                .addOperation(new DivPoly(input))
                .addOperation(new Scalar(input))
                .addOperation(new Euclid(input))
                .addOperation(new EuclidExtended(input))
                .addOperation(new EquivalentInMod(input))
                .addOperation(new Irreducible(input))
                .addOperation(new Generate(input))
                .addOperation(new AdditionTable(input))
                .addOperation(new MultiplicationTable(input))
                .addOperation(new Add(input))
                .addOperation(new Product(input))
                .addOperation(new Quotient(input))
        ;

        input.read();
    }

}
