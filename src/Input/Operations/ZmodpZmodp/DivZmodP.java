package Input.Operations.ZmodpZmodp;

import Input.Input;
import Input.ZmodpZmodpTemplate;
import Values.ZmodP;

public class DivZmodP extends ZmodpZmodpTemplate {
    public DivZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "zmodp/div";
    }

    @Override
    public String getReturnValueDescription() {
        return "(a / b) mod p";
    }

    @Override
    public ZmodP calculate() {
        return a.div(b);
    }
}
