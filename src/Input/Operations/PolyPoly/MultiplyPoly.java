package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.Polynomial;

public class MultiplyPoly extends PolyPolyTemplate {
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
