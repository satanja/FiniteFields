package Input.Operations.PolyPolyPoly;

import Input.Input;
import Input.PolyPolyPolyTemplate;

public class EquivalentInMod extends PolyPolyPolyTemplate {
    public EquivalentInMod(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/equivmod";
    }

    @Override
    public String getReturnValueDescription() {
        return "a (mod c) == b (mod c)";
    }

    @Override
    public Boolean calculate() {
        return a.equalMod(b, c);
    }

    @Override
    public String computationOutput() {
        return calculate().toString();
    }
}
