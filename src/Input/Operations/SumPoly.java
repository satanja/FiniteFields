package Input.Operations;

import Input.Input;
import Input.Poly_Poly_Template;
import Values.Polynomial;

public class SumPoly extends Poly_Poly_Template {
    public SumPoly(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/sum";
    }

    @Override
    public String getReturnValueDescription() {
        return "a + b";
    }

    @Override
    public Polynomial calculate() {
        return a.add(b);
    }
}
