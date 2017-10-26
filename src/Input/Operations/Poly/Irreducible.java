package Input.Operations.Poly;

import Input.Input;
import Input.PolyTemplate;

public class Irreducible extends PolyTemplate {
    public Irreducible(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/irreducible";
    }

    @Override
    public String getReturnValueDescription() {
        return "true if irreducible, else false";
    }

    @Override
    public Boolean calculate() {
        return a.isIrreducible();
    }

    @Override
    public String computationOutput() {
        return calculate().toString();
    }
}
