package Input;

import Values.Exceptions.PValuesNotEqualException;
import Values.ZmodP;

public abstract class ZmodpZmodpTemplate implements OperationInterface {

    private Input input;
    protected ZmodP a;
    protected ZmodP b;

    public ZmodpZmodpTemplate(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        input.printOutput("Expects two Values.ZmodP objects a and b as input with equal p.");
        input.printOutput(getReturnDescription());

        a = input.readZmodP("a");
        b = input.readZmodP("b");

        try {
            input.printOutput(computationOutput()); // Print the output is calculation was successfull.
        } catch (PValuesNotEqualException e) {
            input.printOutput("FAILED: The p values of both Values.ZmodP objects should be equal.");
        } catch (Exception e) {
            input.printOutput("FAILED: Computation failed, reason: '" + e.getMessage() + "'");
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return "Returns a Values.ZmodP value object with value " + getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract ZmodP calculate();

    @Override
    public String computationOutput() {
        return calculate().toString();
    }
}
