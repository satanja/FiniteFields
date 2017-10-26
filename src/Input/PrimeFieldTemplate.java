package Input;

import Values.FiniteField;

public abstract class PrimeFieldTemplate implements OperationInterface {

    private Input input;
    protected FiniteField a;
    protected FiniteField b;

    public PrimeFieldTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects two fields a and b.");
        input.printOutput(getReturnDescription());

        a = input.readField("a");
        a = input.readField("b");

        try {
            input.printOutput(computationOutput()); // Print the output is calculation was successful.
        } catch (Exception e) {
            input.printOutput("FAILED: Computation failed, reason: '" + e.getMessage() + "'");
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract Object calculate();

    @Override
    public String computationOutput() {
        return calculate().toString();
    }
}
