package Input.Operations;

import Input.Input;
import Input.Poly_Poly_Template;
import Values.Polynomial;

public class MultiplyPoly extends Poly_Poly_Template {
    public MultiplyPoly(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/mul";
    }

    @Override
    public String getReturnValueDescription() {
        return "a * b";
    }

    @Override
    public Polynomial calculate() {
        return a.multiply(b);
    }
}
