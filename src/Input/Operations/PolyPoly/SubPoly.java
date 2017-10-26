package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.Polynomial;

public class SubPoly extends PolyPolyTemplate {
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
