package Input.Operations;

import Input.Input;
import Input.ZmodP_ZmodP_Template;
import Values.ZmodP;

public class AddZmodP extends ZmodP_ZmodP_Template {
    public AddZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "add";
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
