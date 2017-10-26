package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.PolyPair;

public class EuclidExtended extends PolyPolyTemplate {
    public EuclidExtended(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/euclidex";
    }

    @Override
    public String getReturnValueDescription() {
        return "x and y with gcd(a,b) = a*x+b*y";
    }

    @Override
    public Object calculate() {
        return a.extendedEuclid(b);
    }

    @Override
    public String computationOutput() {
        PolyPair pair = a.extendedEuclid(b);

        return "x: " + pair.getP1() + "\ty: " + pair.getP2();
    }
}
