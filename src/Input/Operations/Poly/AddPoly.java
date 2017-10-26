package Input.Operations.Poly;

import Input.Input;
import Input.Poly_Poly_Template;
import Values.Polynomial;

public class AddPoly extends Poly_Poly_Template {
    public AddPoly(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/add";
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
