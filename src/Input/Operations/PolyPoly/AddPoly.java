package Input.Operations.PolyPoly;

import Input.Input;
import Input.PolyPolyTemplate;
import Values.Polynomial;

public class AddPoly extends PolyPolyTemplate {
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
