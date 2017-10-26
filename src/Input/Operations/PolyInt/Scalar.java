package Input.Operations.PolyInt;

import Input.Input;
import Input.PolyIntTemplate;
import Values.Polynomial;

public class Scalar extends PolyIntTemplate {
    public Scalar(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "poly/scalar";
    }

    @Override
    public String getReturnValueDescription() {
        return "a * b";
    }

    @Override
    public Polynomial calculate() {
        return a.scarlarMultiply(b);
    }
}
