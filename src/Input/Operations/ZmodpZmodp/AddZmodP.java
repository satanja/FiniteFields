package Input.Operations.ZmodpZmodp;

import Input.Input;
import Input.ZmodpZmodpTemplate;
import Values.ZmodP;

public class AddZmodP extends ZmodpZmodpTemplate {
    public AddZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "zmodp/add";
    }

    @Override
    public String getReturnValueDescription() {
        return "(a + b) mod p";
    }

    @Override
    public ZmodP calculate() {
        return a.add(b);
    }
}
