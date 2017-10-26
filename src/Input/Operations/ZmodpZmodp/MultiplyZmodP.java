package Input.Operations.ZmodpZmodp;

import Input.Input;
import Input.ZmodpZmodpTemplate;
import Values.ZmodP;

public class MultiplyZmodP extends ZmodpZmodpTemplate {
    public MultiplyZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "zmodp/mul";
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
