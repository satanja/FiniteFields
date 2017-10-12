package Input;

import Values.Exceptions.PValuesNotEqualException;
import Values.ZmodP;

public abstract class ZmodP_ZmodP_Template implements OperationInterface {

    private Input input;
    protected ZmodP a;
    protected ZmodP b;

    public ZmodP_ZmodP_Template(Input input) {
        this.input = input;
    }

    @Override
    public void execute() {
        System.out.println(input.getOutputPrefix() + "Expects two Values.ZmodP objects a and b as input with equal p.");
        System.out.println(input.getOutputPrefix() + getReturnDescription());

        a = input.readZmodP("a");
        b = input.readZmodP("b");

        try {
            ZmodP result = calculate();
            System.out.println(input.getOutputPrefix() + result.toString()); // Print the output is calculation was successfull.
        } catch (PValuesNotEqualException e) {
            System.out.println(input.getOutputPrefix() + "FAILED: The p values of both Values.ZmodP objects should be equal.");
        } catch (Exception e) {
            System.out.println(input.getOutputPrefix() + "FAILED: Computation failed, reason: '" + e.getMessage() + "'");
        }
    }

    public abstract String getCommand();

    @Override
    public String getReturnDescription() {
        return input.getOutputPrefix() + "Returns a Values.ZmodP value object with value " + getReturnValueDescription();
    }

    protected abstract String getReturnValueDescription();

    public abstract ZmodP calculate();
}
