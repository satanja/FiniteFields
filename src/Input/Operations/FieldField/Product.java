package Input.Operations.FieldField;

import Input.FieldFieldTemplate;
import Input.Input;
import Values.FiniteField;

public class Product extends FieldFieldTemplate {
    public Product(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "field/mul";
    }

    @Override
    public String getReturnValueDescription() {
        return "a * b";
    }

    @Override
    public FiniteField calculate() {
        return a.multiply(b);
    }
}
