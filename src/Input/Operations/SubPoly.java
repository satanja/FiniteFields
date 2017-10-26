package Input.Operations;

import Input.Input;
import Input.Poly_Poly_Template;
import Values.Polynomial;

public class SubPoly extends Poly_Poly_Template {
    public SubPoly(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/sub";
    }

    @Override
    public String getReturnValueDescription() {
        return "a - b";
    }

    @Override
    public Polynomial calculate() {
        return a.sub(b);
    }
}
