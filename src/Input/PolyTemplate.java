package Input;

import Values.Polynomial;

public abstract class PolyTemplate implements OperationInterface {

    private Input input;
    protected Polynomial a;

    public PolyTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects a polynomial a.");
        input.printOutput(getReturnDescription());

        a = input.readPolynomial("a");

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
