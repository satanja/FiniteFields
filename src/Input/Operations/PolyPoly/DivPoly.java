package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.PolyPair;

public class DivPoly extends PolyPolyTemplate {
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
