package Input;

import Values.Polynomial;

public abstract class PolyIntTemplate implements OperationInterface {

    private Input input;
    protected Polynomial a;
    protected int b;

    public PolyIntTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects a polynomial a and an integer b as input.");
        input.printOutput(getReturnDescription());

        a = input.readPolynomial("a");

        input.printOutput("Successfully read polynomial a, as a = " + a.toString());

        b = input.readInt("b");

        input.printOutput("Successfully read integer b, as b = " + b);

        try {
            input.printOutput(computationOutput()); // Print the output is calculation was successful.
        } catch (Exception e) {
            input.printOutput("FAILED: Computation failed, reason: '" + e.getMessage() + "'");
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
        return calculate().toString();
    }
}
