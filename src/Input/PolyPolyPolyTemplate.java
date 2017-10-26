package Input;

import Values.Polynomial;

public abstract class PolyPolyPolyTemplate implements OperationInterface {

    private Input input;
    protected Polynomial a;
    protected Polynomial b;
    protected Polynomial c;

    public PolyPolyPolyTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects three polynomials a, b and c as input.");
        input.printOutput(getReturnDescription());

        a = input.readPolynomial("a");
        b = input.readPolynomial("b");
        c = input.readPolynomial("c");

        try {
            input.printOutput(computationOutput()); // Print the output is calculation was successful.
        } catch (Exception e) {
            input.printOutput("FAILED: Computation failed, reason: '" + e.getMessage() + "'\n" + e.toString());
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return "Returns a polynomial with value " + getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract Object calculate();

    @Override
    public String computationOutput() {
        return "Output: " + calculate().toString();
    }
}
