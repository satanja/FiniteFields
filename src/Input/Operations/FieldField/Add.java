package Input.Operations.FieldField;

import Input.FieldFieldTemplate;
import Input.Input;
import Values.FiniteField;

public class Add extends FieldFieldTemplate {
    public Add(Input input) {
        super(input);
    }

    @Override
    public String getCommand() {
        return "field/add";
    }

    @Override
    public String getReturnValueDescription() {
        return "a + b";
    }

    @Override
    public FiniteField calculate() {
        return a.add(b);
    }
}
