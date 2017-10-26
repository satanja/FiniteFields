package Input;

public abstract class IntTemplate implements OperationInterface {

    private Input input;
    protected int a;

    public IntTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects an integer a.");
        input.printOutput(getReturnDescription());

        a = input.readInt("a");

        try {
            input.printOutput(computationOutput()); // Print the output is calculation was successful.
        } catch (Exception e) {
            input.printOutput("FAILED: Computation failed, reason: '" + e.getMessage() + "'");
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return "Returns " + getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract Object calculate();

    @Override
    public String computationOutput() {
        return calculate().toString();
    }
}
