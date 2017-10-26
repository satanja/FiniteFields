package Input.Operations;

import Input.Input;
import Input.ZmodP_ZmodP_Template;
import Values.ZmodP;

public class RemainderZmodP extends ZmodP_ZmodP_Template {
    public RemainderZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "zmodp/remainder";
    }

    @Override
    public String getReturnValueDescription() {
        return "(a % b) mod p";
    }

    @Override
    public ZmodP calculate() {
        return a.remainder(b);
    }
}
