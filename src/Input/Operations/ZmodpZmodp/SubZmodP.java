package Input.Operations.ZmodpZmodp;

import Input.Input;
import Input.ZmodpZmodpTemplate;
import Values.ZmodP;

public class SubZmodP extends ZmodpZmodpTemplate {
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
