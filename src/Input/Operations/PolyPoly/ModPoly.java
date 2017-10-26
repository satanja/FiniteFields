package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.Polynomial;

public class ModPoly extends PolyPolyTemplate {
    public ModPoly(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/mod";
    }

    @Override
    public String getReturnValueDescription() {
        return "a mod b";
    }

    @Override
    public Polynomial calculate() {
        return a.mod(b);
    }
}
