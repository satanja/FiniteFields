package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.Polynomial;

public class Euclid extends PolyPolyTemplate {
    public Euclid(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/euclid";
    }

    @Override
    public String getReturnValueDescription() {
        return "gcd(a,b)";
    }

    @Override
    public Polynomial calculate() {
        return a.euclid(b);
    }
}
