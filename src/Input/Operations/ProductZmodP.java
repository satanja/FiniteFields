package Input.Operations;

import Input.Input;
import Input.ZmodP_ZmodP_Template;
import Values.ZmodP;

public class ProductZmodP extends ZmodP_ZmodP_Template {
    public ProductZmodP(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "zmodp/product";
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
