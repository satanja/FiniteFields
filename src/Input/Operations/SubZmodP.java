package Input.Operations;

import Input.Input;
import Input.ZmodP_ZmodP_Template;
import Values.ZmodP;

public class SubZmodP extends ZmodP_ZmodP_Template {
    public SubZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "zmodp/sub";
    }

    @Override
    public String getReturnValueDescription() {
        return "(a - b) mod p";
    }

    @Override
    public ZmodP calculate() {
        return a.sub(b);
    }
}
