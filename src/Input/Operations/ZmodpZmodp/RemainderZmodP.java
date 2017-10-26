package Input.Operations.ZmodpZmodp;

import Input.Input;
import Input.ZmodpZmodpTemplate;
import Values.ZmodP;

public class RemainderZmodP extends ZmodpZmodpTemplate {
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
