package Input.Operations.FieldField;

import Input.FieldFieldTemplate;
import Input.Input;
import Values.FiniteField;

public class Quotient extends FieldFieldTemplate {
    public Quotient(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "field/quo";
    }

    @Override
    public String getReturnValueDescription() {
        return "a * b^-1";
    }

    @Override
    public FiniteField calculate() {
        return a.qoutient(b);
    }
}
