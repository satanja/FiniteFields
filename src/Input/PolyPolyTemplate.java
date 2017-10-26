package Input;

import Values.Polynomial;

public abstract class PolyPolyTemplate implements OperationInterface {

    private Input input;
    protected Polynomial a;
    protected Polynomial b;

    public PolyPolyTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects two polynomials a and b as input.");
        input.printOutput(getReturnDescription());

        a = input.readPolynomial("a");
        b = input.readPolynomial("b");

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
        return "Output: " + calculate().toString();
    }
}
