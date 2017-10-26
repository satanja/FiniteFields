package Input.Operations.Poly;

import Input.Input;
import Input.Poly_Poly_Template;
import Values.PolyPair;

public class DivPoly extends Poly_Poly_Template {
    public DivPoly(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/div";
    }

    @Override
    public String getReturnValueDescription() {
        return "a mod b";
    }

    @Override
    public Object calculate() {
        return a.longDivision(b);
    }

    @Override
    public String computationOutput() {
        PolyPair pair = a.longDivision(b);

        return "Quotient: " + pair.getP1() + "\tRemainder: " + pair.getP2();
    }
}
