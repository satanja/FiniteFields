package Input.Operations;

import Input.Input;
import Input.Poly_Poly_Template;
import Values.Polynomial;

public class ModPoly extends Poly_Poly_Template {
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
