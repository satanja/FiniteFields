package Input.Operations;

import Input.Input;
import Input.ZmodP_ZmodP_Template;
import Values.ZmodP;

public class MultiplyZmodP extends ZmodP_ZmodP_Template {
    public MultiplyZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "multiply";
    }

    @Override
    public String getReturnValueDescription() {
        return "(a * b) mod p";
    }

    @Override
    public ZmodP calculate() {
        return a.multiply(b);
    }
}
